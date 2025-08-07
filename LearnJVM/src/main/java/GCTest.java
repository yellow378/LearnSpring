public class GCTest {

    private static  volatile Integer value = 22;

    public static synchronized Integer  getAndIncr(){
        Integer temp = value;
        value++;
        return temp;
    }



    public static void main(String[] args) throws InterruptedException {
        //print use what's gc
        //Thread.sleep(20000);
        synchronized (GCTest.class){
            System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"MB");
            byte[] bytes = new byte[1024*1024*1024];
            for(int i=0;i<bytes.length;i++){
                bytes[i]=(byte)i;
            }
            System.out.println(bytes[1024]);
            System.gc();
            System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"MB");

        }
    }
}
