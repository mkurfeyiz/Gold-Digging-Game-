package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class B extends Players{


    public B(int moveCost,int targetCost,int steps,int gold,int indexI,int indexJ,int matrixM,int matrixN){

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;
        this.indexI = indexI;
        this.indexJ = indexJ;
        this.matrixM = matrixM;
        this.matrixN = matrixN;

        try {
            this.logFile = new File("src/sample/Logs/logs.txt");
            if (logFile.createNewFile()) {
                System.out.println(logFile.getName() + " dosyası oluşturuldu.");
            }

        } catch (IOException e) {
            System.out.println("Bir hata meydana geldi.");
            e.printStackTrace();
        }

    }

    //En yakindaki en karli altini hedefler
    @Override
    public void selectTarget(int matrix[][],int targetCost,int playerI,int playerJ){
        //Search algorithm
        double tempDist = 1000;
        double totalProfit = -1;
        double tempProfit ;
        int tempI = 0,tempJ = 0;
        int tempGold = 1;
        playerI = this.indexI;
        playerJ = this.indexJ;

        for (int i=0;i<matrixM;i++){
            for(int j=0;j<matrixN;j++){
                if(matrix[i][j]!=1 && matrix[i][j]!=50 && calcDistance(playerI,playerJ,i,j) < tempDist){
                    tempGold = matrix[i][j];
                    tempDist = calcDistance(playerI,playerJ,i,j);
                    tempProfit = (tempGold-(Math.ceil(tempDist/this.steps))*this.moveCost-this.targetCost);
                    if(tempProfit > totalProfit){
                        totalProfit = tempProfit;
                        tempI = i;
                        tempJ = j;
                    } else {
                        tempGold = 1;
                        tempDist = 1000;
                        totalProfit = -1;
                    }
                    System.out.println("B oyuncusunun hedefi "+tempI+","+tempJ+" ve hedefteki altın miktarı : "+matrix[tempI][tempJ]);
                }
            }
        }

        System.out.println("\nB oyuncusunun hedefi "+tempI+","+tempJ+" ve hedefteki altın miktarı : "+matrix[tempI][tempJ]);
        System.out.println("Uzaklık : "+Math.ceil(tempDist/this.steps));
        System.out.println("Kazanç : "+totalProfit);

        this.gold -= targetCost;
        this.goldSpent += targetCost;
    }

    @Override
    public void log(Players player) {
        //logB.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        System.out.println(player.logFile.getName()+" dosyasına yazılıyor...");
        try {
            writer = new FileWriter("src/sample/Logs/logs.txt",true);
            writer.write("\nB Oyuncusu\n\n");
            writer.write("Kalan Altın Miktarı : "+player.gold+" \nHarcanan Toplam Altın Miktarı :"+player.goldSpent
                    +" \nToplam Atılan Adım Sayısı : "+player.stepsCount);
            writer.write("\n\n----\n");
            writer.close();
            System.out.println("Yazma işlemi tamamlandı!");
        }
        catch (Exception e) {
            System.out.println("Bir sorun meydana geldi.");
            e.printStackTrace();
        }

    }



}
