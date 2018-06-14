package test;

        import thinking.net.mindview.util.RandomGenerator;

        import java.util.*;
        import java.util.concurrent.ConcurrentHashMap;
        import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {


        public static void main(String[] args) {


            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < 100; i++) {
                map.put(i + "", "value" + i);
            }
            //第一种方式：通过遍历Map.keySet()遍历HashMap的key和value
            firstMethod(map);

            //第二种方式：通过遍历values()遍历Map的value，但是不能遍历key
            secondMethod1(map);
            secondMethod2(map);

            //第三种方式：通过Map.entrySet()使用iterator()遍历HashMap的key和value
            thirdMethod1(map);
            thirdMethod2(map);
        }



        private static void firstMethod(Map<String, String> map) {
            long startTime = System.currentTimeMillis();
            System.out.println("第一种方式：通过遍历Map.keySet()遍历HashMap的key和value");
            for (String key : map.keySet()) {
                System.out.println("key= "+ key + " and value= " + map.get(key));
            }
            System.out.println("第一种耗时:"+(System.currentTimeMillis() - startTime));
        }


        private static void secondMethod1(Map<String, String> map) {
            long startTime = System.currentTimeMillis();
            startTime = System.currentTimeMillis();
            System.out.println("第二种方式1：通过遍历values()遍历Map的value，但是不能遍历key");
            Collection<String> values = map.values();
            for(Iterator<String> it2 = values.iterator();it2.hasNext();){
                it2.next();
            }

            System.out.println("第二种耗时1耗时:"+(System.currentTimeMillis() - startTime));
        }

        private static void secondMethod2(Map<String, String> map) {
            long startTime = System.currentTimeMillis();
            startTime = System.currentTimeMillis();
            System.out.println("第二种方式2：通过遍历values()遍历Map的value，但是不能遍历key");
            for (String v : map.values()) {
                System.out.println("value= " + v);
            }
            System.out.println("第二种方式2耗时:"+(System.currentTimeMillis() - startTime));
        }


        private static void thirdMethod1(Map<String, String> map) {
            long startTime = System.currentTimeMillis();
            startTime = System.currentTimeMillis();
            System.out.println("第三种方式1：通过Map.entrySet()使用iterator()遍历HashMap的key和value");
            Iterator<Map.Entry<String, String>> it3 = map.entrySet().iterator();
            while(it3.hasNext()){
                Map.Entry<String, String> entry = it3.next();
                entry.getKey();
                entry.getValue();
                System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
            }

            System.out.println("第三种方式1耗时:"+(System.currentTimeMillis() - startTime));
        }

        private static void thirdMethod2(Map<String, String> map) {
            long startTime = System.currentTimeMillis();
            startTime = System.currentTimeMillis();
            System.out.println("第三种方式2：通过Map.entrySet()使用iterator()遍历HashMap的key和value");
            //map容量大时用此种遍历方式
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println("key： " + entry.getKey() + " and value： " + entry.getValue());
            }
            System.out.println("第三种方式2耗时:"+(System.currentTimeMillis() - startTime));
        }


}



