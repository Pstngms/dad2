package JumpMyBallApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductEntity {
    int id;
    String title;
    String productType;
    int articleNumber;
    String description;
    String image;
    int productionPersonCount;
    int productionWorkshopNumber;
    int minCostForAgent;

    public ProductEntity(String title, String productType, int articleNumber, String description, String image, int productionPersonCount, int productionWorkshopNumber, int minCostForAgent) {
        this(-1,title,productType,articleNumber,description,image,productionPersonCount,productionWorkshopNumber,minCostForAgent);
    }
}
