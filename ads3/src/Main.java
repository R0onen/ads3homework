import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int hashTableSize = 20;
        MyHashTable<Residents, Integer> hashTable = new MyHashTable<>(hashTableSize);
        for(int i = 0; i < 10000; i++){
            hashTable.put(new Residents(randomString(15), new Random().nextInt(100)), new Random().nextInt(10000));
        }
    }
    public static String randomString(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}