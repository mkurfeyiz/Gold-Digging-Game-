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

                    if(matrix[i][j] != 1 && matrix[i][j] != 50){

                        tempProfit = (matrix[i][j]-(Math.ceil(calcDistance(playerI,playerJ,i,j)/this.steps))*this.moveCost-this.targetCost);

                        if(tempProfit > totalProfit &&
                                ((i != a.targetI && j != a.targetJ ) && (i != b.targetI && j != b.targetJ) &&
                                (i != c.targetI && j != c.targetJ))){

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

        this.gold -= this.targetCost;
        this.goldSpent += this.targetCost;
        this.flag = true;

        setStepsRemaining();
    }

    @Override
    public void log(Players player) {
        //logD.txt dosyasi olustur ve icine hamle bilgilerini yazdir.

        try {
            writer = new FileWriter("src/sample/Logs/logD.txt", true);
            writer.write("\nD Oyuncusu\n\n");
            writer.write("Kalan Altın Miktarı : " + player.gold + " \nHarcanan Toplam Altın Miktarı :" + player.goldSpent
                    + " \nToplam Atılan Adım Sayısı : " + player.stepsCount);
            writer.write("\n----\n");
            writer.close();

        } catch (Exception e) {
            System.out.println("logD.txt dosyasına yazma işlemi sırasında bir sorun meydana geldi.");
            e.printStackTrace();
        }

    }

    @Override
    public void movement(int[][] matrix,Players a,Players b,Players c) {

        int counter = 0;

        while(counter < this.steps){

            if(this.indexI != this.targetI){
                if(this.indexI < this.targetI){
                    this.indexI++;
                    this.stepsCount++;
                    counter++;
                } else if(this.indexI > targetI){
                    this.indexI--;
                    this.stepsCount++;
                    counter++;
                }
            }

            if(this.indexJ != this.targetJ){
                if(this.indexJ < this.targetJ){
                    this.indexJ++;
                    this.stepsCount++;
                    counter++;
                } else if(this.indexJ > targetJ){
                    this.indexJ--;
                    this.stepsCount++;
                    counter++;
                }
            }

            //Getting another players target
            if(this.indexI == b.targetI && this.indexJ == b.targetJ){
                //Stealing from b
                System.out.println("D oyuncusu,B oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                b.flag = false;

            }  if(this.indexI == c.targetI && this.indexJ == c.targetJ){
                //Stealing from c
                System.out.println("D oyuncusu,C oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                c.flag = false;

            }  if(this.indexI == a.targetI && this.indexJ == a.targetJ){
                //Stealing from a
                System.out.println("D oyuncusu,A oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                a.flag = false;

            }

            if(this.indexI == targetI && this.indexJ == this.targetJ && matrix[this.indexI][this.indexJ] != 1){
                System.out.println("D oyuncusu hedefine ulaştı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                this.flag = false;
                break;
            } else if(this.indexI == targetI && this.indexJ == this.targetJ && matrix[this.indexI][this.indexJ] == 1){

                this.flag = false;
                break;
            }
        }

        this.gold -= this.moveCost;
        this.goldSpent += this.moveCost;

    }


}
