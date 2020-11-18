package sample.Players;

public class B extends Players{

    public B(int moveCost,int targetCost,int steps,int gold){

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;

    }

    //En yakindaki en karli altini hedefler
    @Override
    public void selectTarget(int moveCost,int targetCost){
        //Search algorithm

        this.gold -= moveCost;
        this.gold -= targetCost;
        this.goldSpent = moveCost + targetCost;
    }

    @Override
    public void log(Players player) {
        //logB.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
    }

}
