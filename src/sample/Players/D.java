package sample.Players;

public class D extends Players{

    public D(int moveCost,int targetCost,int steps,int gold){

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;

    }

    //Diğer oyuncuların yapacağı hamleleri önceden sezme yeteneği bulunur. Diğer
    //oyuncuların hedeflediği altınlara onlardan önce erişemiyorsa bu altınları hariç tutar ve hedef
    //olarak diğer altın kareler içerisinden en karlı olanı seçer.
    @Override
    public void selectTarget(int moveCost,int targetCost){
        //Search algorithm

        this.gold -= moveCost;
        this.gold -= targetCost;
        this.goldSpent = moveCost + targetCost;
    }

    @Override
    public void log(Players player) {
        //logD.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
    }

}
