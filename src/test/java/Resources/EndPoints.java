package Resources;

public enum EndPoints {


    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    EndPoints(String endPoint){
        this.endPoint = endPoint;
    }

    public String getEndPoint(){
        return endPoint;
    }

    private String endPoint;
}
