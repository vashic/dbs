package com.company;

import java.util.Map;
import java.util.TreeMap;
import java.util.*;

public class Bucket {

        int depth,size;
        Map< Integer,String > values=new TreeMap<Integer, String>();
        public Bucket(int depth,int size)
        {
            this.depth=depth;
            this.size=size;
        }


        public int insert(int key,String value)
        {

            if(values.containsKey(key))
            {
                //System.out.println("Key already exists");
                return -1;
            }
           if(values.size()==size)
               return 0;
            values.put(key,value);
            //System.out.println("key inserted");
            return 1;
        }
        public int remove(int key)
        {
            if(values.containsKey(key))
            {
                values.remove(key);
                return 1;
            }
            else
            {
                System.out.println("Key doesn't exist");
                return 0;
            }
        }
        public void search(int key)
        {
            if(values.containsKey(key))
            {
                System.out.println(key + " "+ values.get(key));
            }
        }



        public int isEmpty()
        {
            if (values.size()==size)
            {
                return 1;
            }
            else
                return 0;
        }

        public int getDepth()
        {
            return depth;
        }

        public int decreaseDepth()
        {
            depth--;
            return depth;
        }
        public int increaseDepth()
        {
            depth++;
            return depth;
        }

        public void clear()
        {
            values.clear();

        }

        public TreeMap<Integer,String> copy()
        {
            TreeMap<Integer,String> temp= new TreeMap<Integer, String>();
            temp.putAll(values);
           // System.out.println(temp);
            return temp;

        }

        public void display()
        {
            System.out.println(values.values());
        }


    }


