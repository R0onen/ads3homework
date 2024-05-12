public class Residents {
    private String name;
    private int flat;
    Residents(String name, int flat){
        this.name = name;
        this.flat = flat;
    }
    public int hashCode(){
        int hash = 0;
        for(int i = 0; i < name.length(); i++){
            hash = name.toCharArray()[i] + (hash*17);
        }
        hash += flat;
        return hash;
    }
}
