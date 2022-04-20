public class Calculation {
    public int getQuantityForProduct(int productType,int materialType,int count, float width, float length){
        double d=1;
        switch (productType){
            case 1:
                d*=1.1;
                break;
            case 2:
                d*=2.5;
                break;
            case 3:
                d*=8.43;
                break;
            default:
                return -1;
        }
        switch (materialType){
            case 1:
                d*=1.03;
                break;
            case 2:
                d*=1.12;
                break;
            default:
                return -1;
        }
        if(count<1 || width<=0 ||)
    }
}
