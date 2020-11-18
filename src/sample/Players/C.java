package sample.Players;

public class C extends Players{

    public C(int moveCost,int targetCost,int steps,int gold){

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;

    }

    //Hedef belirlemeden once kendine en yakin belirli sayidaki gizli altini acar.
    // Bu açılan altınlarla birlikte tüm altınlar içerisinden B oyuncusu
    //gibi en karlı olanı hedefler
    @Override
    public void selectTarget(int moveCost,int targetCost){
        //Search algorithm

        this.gold -= moveCost;
        this.gold -= targetCost;
        this.goldSpent = moveCost + targetCost;
    }

    @Override
    public void log(Players player) {
        //logC.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
    }

}
