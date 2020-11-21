package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class D extends Players {

    int points[] = {5, 10, 15, 20};

    public D(int moveCost, int targetCost, int steps, int gold, int indexI, int indexJ, int matrixM, int matrixN) {

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;
        this.indexI = indexI;
        this.indexJ = indexJ;
        this.matrixM = matrixM;
        this.matrixN = matrixN;

        try {
            this.logFile = new File("src/sample/Logs/logD.txt");
            if (logFile.createNewFile()) {
                System.out.println(logFile.getName() + " dosyası oluşturuldu.");
            }

        } catch (IOException e) {
            System.out.println("Bir hata meydana geldi.");
            e.printStackTrace();
        }

    }

    //Diğer oyuncuların yapacağı hamleleri önceden sezme yeteneği bulunur. Diğer
    //oyuncuların hedeflediği altınlara onlardan önce erişemiyorsa bu altınları hariç tutar ve hedef
    //olarak diğer altın kareler içerisinden en karlı olanı seçer.
    @Override
    public void selectTarget(int matrix[][], int targetCost, int indexI, int indexJ) {
        //Search algorithm


        this.gold -= targetCost;
        this.goldSpent += targetCost;
    }

    //Digerlerinin hedefine onlardan once erisebiliyosa hedef olarak onu belirler.Ancak gidemiyosa
    //bunlari haric tutar ve B nin algoritmasini kullanir.
    public void selectTarget(int matrix[][], int targetCost, int playerI, int playerJ, A a, B b, C c) {
        //
        double tempDist = 1000;
        double totalProfit = -200;
        double tempProfit;
        int tempI = 0, tempJ = 0;
        boolean control = true;

        playerI = this.indexI;
        playerJ = this.indexJ;


        //Eger d ile a'nın hedefi arasındaki mesafe, a'nın kendi hedefi ile arasındaki mesafeden kücükse
        if (calcDistance(playerI, playerJ, a.getTargetI(), a.getTargetJ()) <
                calcDistance(a.getIndexI(), a.getIndexJ(), a.getTargetI(), a.getTargetJ())) {

            tempDist = calcDistance(playerI, playerJ, a.getTargetI(), a.getTargetJ());
            tempI = a.getTargetI();
            tempJ = a.getTargetJ();

            System.out.println("\nD,A'nın hedefine daha yakın olduğu için "+tempI+","+tempJ+" noktasını hedefledi.");
            System.out.println("Hedefteki altın miktarı : "+matrix[tempI][tempJ]);
            System.out.println("Yapacağı Hamle Sayısı : "+(int)Math.ceil(tempDist / this.steps));

        } else if (calcDistance(playerI, playerJ, b.getTargetI(), b.getTargetJ()) < //d ile b
                calcDistance(b.getIndexI(), b.getIndexJ(), b.getTargetI(), b.getTargetJ())) {

            tempDist = calcDistance(playerI, playerJ, b.getTargetI(), b.getTargetJ());
            tempI = b.getTargetI();
            tempJ = b.getTargetJ();

            System.out.println("\nD,B'nin hedefine daha yakın olduğu için "+tempI+","+tempJ+" noktasını hedefledi.");
            System.out.println("Hedefteki altın miktarı : "+matrix[tempI][tempJ]);
            System.out.println("Yapacağı Hamle Sayısı : "+(int)Math.ceil(tempDist / this.steps));

        } else if (calcDistance(playerI, playerJ, c.getTargetI(), c.getTargetJ()) < //d ile c
                calcDistance(c.getIndexI(), c.getIndexJ(), c.getTargetI(), c.getTargetJ())) {

            tempDist = calcDistance(playerI, playerJ, c.getTargetI(), c.getTargetJ());
            tempI = c.getTargetI();
            tempJ = c.getTargetJ();

            System.out.println("\nD,C'nin hedefine daha yakın olduğu için "+tempI+","+tempJ+" noktasını hedefledi.");
            System.out.println("Hedefteki altın miktarı : "+matrix[tempI][tempJ]);
            System.out.println("Yapacağı Hamle Sayısı : "+(int)Math.ceil(tempDist / this.steps));

        } else {

            control = false;

        }


        if (!control) {

            for (int i=0;i<matrixM;i++){

                for(int j=0;j<matrixN;j++){

                    if(matrix[i][j] != 1 && matrix[i][j]!=50){

                        tempProfit = (matrix[i][j]-(Math.ceil(calcDistance(playerI,playerJ,i,j)/this.steps))*this.moveCost-this.targetCost);

                        if(tempProfit > totalProfit &&
                                (i != a.targetI && i != b.targetI && i != c.targetI && j != a.targetJ && j != b.targetJ && j != c.targetJ)){

                            totalProfit = tempProfit;
                            tempDist = calcDistance(playerI,playerJ,i,j);
                            tempI = i;
                            tempJ = j;

                        } else {
                            continue;
                        }
                    }
                }
            }

            System.out.println("\nD oyuncusunun hedefi "+tempI+","+tempJ+" ve hedefteki altın miktarı : "+matrix[tempI][tempJ]);
            System.out.println("Yapacağı Hamle Sayısı : "+(int)Math.ceil(tempDist/this.steps));
            System.out.println("Kazanç : "+(int)totalProfit);

        }


        this.targetI = tempI;
        this.targetJ = tempJ;
        this.targetDist = (int) Math.ceil(tempDist / this.steps);

        this.gold -= targetCost;
        this.goldSpent += targetCost;
    }

    @Override
    public void log(Players player) {
        //logD.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        System.out.println(player.logFile.getName() + " dosyasına yazılıyor...");
        try {
            writer = new FileWriter("src/sample/Logs/logD.txt", true);
            writer.write("\nD Oyuncusu\n\n");
            writer.write("Kalan Altın Miktarı : " + player.gold + " \nHarcanan Toplam Altın Miktarı :" + player.goldSpent
                    + " \nToplam Atılan Adım Sayısı : " + player.stepsCount);
            writer.write("\n----\n");
            writer.close();
            System.out.println("Yazma işlemi tamamlandı!");
        } catch (Exception e) {
            System.out.println("Bir sorun meydana geldi.");
            e.printStackTrace();
        }

    }


}
