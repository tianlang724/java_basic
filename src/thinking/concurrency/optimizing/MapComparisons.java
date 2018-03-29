package thinking.concurrency.optimizing;
import java.util.*;
import java.util.concurrent.*;
import thinking.generics.genericsinterface.*;
abstract class MapTest extends Tester<Map<Integer,Integer>>{
    MapTest(String testId,int nReaders,int nWriters){
        super(testId, nReaders, nWriters);
    }
    class Reader extends TestTask{
        long result=0;
        void test(){
            for(long i=0;i<testCycles;i++){
                for(int index=0;index<containerSize;index++){
                    result+=testContainer.get(index);
                }
            }
        }
        void putResult(){
            readResult+=result;
            readTime+=duration;
        }
    }
    class Writer extends TestTask{
        void test(){
            for(long i=0;i<testCycles;i++){
                for(int index=0;index<containerSize;index++){
                    testContainer.put(index,writeData[index]);
                }
            }
        }
        void putResult(){
            writeTime+=duration;
        }
    }
    void startReadersAndWriters(){
        for(int i=0;i<nReaders;i++)
            es.execute(new Reader());
        for(int i=0;i<nWriters;i++){
            es.execute(new Writer());
        }
    }
}
class SynchronizedHashMapTest extends MapTest{
    Map<Integer,Integer> containerInitializer(){
        return Collections.synchronizedMap(
            new HashMap<Integer,Integer>(
            MapData.map(new CountingGenerator.Integer(),new CountingGenerator.Integer(),containerSize)));
    }
    SynchronizedHashMapTest(int nReaders,int nWriters){
        super("SynchronizedHashMap",nReaders,nWriters);
    }
}
class ConcurrentMapTest extends MapTest{
    Map<Integer,Integer> containerInitializer(){
        return new ConcurrentHashMap<Integer,Integer>(
            MapData.map(new CountingGenerator.Integer(),new CountingGenerator.Integer(),containerSize));
    }
    ConcurrentMapTest(int nReaders,int nWriters){
        super("ConcurrentMap",nReaders,nWriters);
    }
}
public class MapComparisons{
    public static void main(String[] args){
        Tester.initMain(args);
        new SynchronizedHashMapTest(10, 0);
        new SynchronizedHashMapTest(9, 1);
        new SynchronizedHashMapTest(5, 5);
        new ConcurrentMapTest(10, 0);
        new ConcurrentMapTest(9, 1);
        new ConcurrentMapTest(5, 5);
    }
}