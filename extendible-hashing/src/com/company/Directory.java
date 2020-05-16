package com.company;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Directory {
    int global_depth,bucket_size;
    ArrayList<Bucket> buckets=new ArrayList<Bucket>();
    public Directory(int depth,int bucket_size)
    {
        this.global_depth=depth;
        this.bucket_size=bucket_size;
        for(int i=0;i<(1<<depth);i++)
        {
            buckets.add(new Bucket(depth,bucket_size));
            //System.out.println(i);
        }
    }

    public int hash(int n)
    {
        return n&((1<<global_depth)-1);
    }

    public int pairIndex(int bucket_no,int depth)
    {
        return bucket_no^(1<<(depth-1));
    }

    public void grow()
    {
        /*for (int i=0;i<1<<global_depth;i++)
        {
            buckets.add(buckets.get(i));
            //System.out.println(i);
        }*/
        global_depth++;
        int depth= buckets.get(0).depth;
        int bucket_size= buckets.get(0).size;
        for( int i = global_depth/2; i< global_depth;i++)
        {
            buckets.add(new Bucket(depth,bucket_size));
        }

    }

    public void shrink()
    {
        int flag1=1;
        for(int i=0;i<buckets.size();i++)
        {
            if(buckets.get(i).getDepth()==global_depth)
            {
                flag1=0;
                return;
            }
        }
        global_depth--;
        for(int i=0;i<1<<global_depth;i++)
            buckets.remove(buckets.size()-1);
    }

    public void insert(int key,String value)
    {

        int bucket_no= hash(key) ;
        System.out.println(key+" "+bucket_no);
        int status= buckets.get(bucket_no).insert(key,value);
        if (status==1) {

            System.out.println("inserted key " + key + " in buckt " + bucket_id(bucket_no));

        }
        else if(status==0)
        {
            System.out.println("split");
            split(bucket_no);
            insert(key,value);

        }
        else
        {
            System.out.println("key already exists");
        }
    }
    public void split(int bucket_no)
    {
        int local_depth,pair_index,index_diff,dir_size,i;
        TreeMap<Integer,String > temp= new TreeMap<Integer, String>();
        local_depth= buckets.get(bucket_no).increaseDepth();
        //System.out.println(local_depth);
        if(local_depth>global_depth)
            grow();
        pair_index=pairIndex(bucket_no,local_depth);

        buckets.add(pair_index,new Bucket(local_depth,bucket_size));
        temp=buckets.get(bucket_no).copy();
        //System.out.println(temp);
        buckets.get(bucket_no).clear();
       // System.out.println("after clear");buckets.get(bucket_no).display();
        index_diff=1<<local_depth;
        dir_size=1<<global_depth;
        System.out.println(pair_index+ " " + index_diff + " " +dir_size);
        for(i=pair_index-index_diff;i>=0;i-=index_diff)
            buckets.add(i,buckets.get(pair_index));
        for(i=pair_index+index_diff;i<dir_size;i+=index_diff)
            buckets.add(i,buckets.get(pair_index));
        for (Map.Entry<Integer,String> e : temp.entrySet())
            insert(e.getKey(),e.getValue());

    }

    public void merge(int bucket_no)
    {
        int local_depth,pair_index,index_diff,dir_size,i;

        local_depth=buckets.get(bucket_no).getDepth();
        pair_index=pairIndex(bucket_no,local_depth);
        index_diff = 1<<local_depth;
        dir_size = 1<<global_depth;

        if(buckets.get(pair_index).getDepth() == local_depth)
        {
            buckets.get(pair_index).decreaseDepth();
           // buckets.get(bucket_no).clear();
            buckets.set(bucket_no,buckets.get(pair_index));
            for (i=bucket_no-index_diff;i>=0;i-=index_diff)
                buckets.set(i,buckets.get(pair_index));
            for(i=bucket_no+index_diff;i<dir_size;i+=index_diff)
                buckets.set(i,buckets.get(pair_index));

        }
    }


    public String bucket_id(int n)
    {
        int d;
        String s;
        d= buckets.get(n).getDepth();
        s="";
        while(n>0 && d>0)
        {
            s= (n/2==0?"0":"1")+s;
            n/=2;
            d--;

        }
        while(d>0)
        {
            s="0"+s;
            d--;
        }
        String r="";
        for(int i = s. length() - 1; i >= 0; i--) {
            r = r + s. charAt(i); }
    return r;



    }



    public void delete(int key,int mode)
    {
        int bucket_no = hash(key);
        if(buckets.get(bucket_no).remove(key)!=0)
            System.out.println("Deleted key "+key+" from bucket "+bucket_id(bucket_no));
        if(mode>0)
        {
            if(buckets.get(bucket_no).isEmpty()!=0 && buckets.get(bucket_no).getDepth()>1)
            merge(bucket_no);
        }
        if(mode>1)
        {
            shrink();
        }
    }
    public void search(int key)
    {
        int bucket_no = hash(key);
        System.out.println("Searching key "+key+" in bucket "+bucket_id(bucket_no));
        buckets.get(bucket_no).search(key);
    }

    public void display(boolean duplicates)
    {
        int i,j,d;
        String s;
        ArrayList<String> shown= new ArrayList<String>();
        System.out.println("Global depth : "+global_depth);
        for(i=0;i<buckets.size();i++)
        {
           /* d = buckets.get(i).getDepth();
            s = bucket_id(i);
            if(duplicates || shown.contains(s))
            {
                shown.add(s);
                for(j=d;j<=global_depth;j++)
                    System.out.println(" ");
                System.out.println(s+ " => ");
                buckets.get(i).display();
            }*/
            System.out.println(i );buckets.get(i).display();
        }
    }


}
