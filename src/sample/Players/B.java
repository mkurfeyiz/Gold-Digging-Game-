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
            this.logFile = new File("src/sample/Logs/logB.txt");
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
        double totalProfit = -100;
        double tempProfit;
        int tempI = 0,tempJ = 0;

        playerI = this.indexI;
        playerJ = this.indexJ;

        for (int i=0;i<matrixM;i++){
            for(int j=0;j<matrixN;j++){
                if(matrix[i][j] != 1 && matrix[i][j] != 50){
                    tempProfit = (matrix[i][j]-(Math.ceil(calcDistance(playerI,playerJ,i,j)/this.steps))*this.moveCost-this.targetCost);
                    if(tempProfit > totalProfit){
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

        System.out.println("\nB oyuncusunun hedefi "+tempI+","+tempJ+" ve hedefteki altın miktarı : "+matrix[tempI][tempJ]);
        System.out.println("Yapacağı Hamle Sayısı : "+(int)Math.ceil(tempDist/this.steps));
        System.out.println("Kazanç : "+(int)totalProfit);

        this.targetI = tempI;
        this.targetJ = tempJ;
        this.targetDist = (int) Math.ceil(tempDist/this.steps);

        this.gold -= targetCost;
        this.goldSpent += targetCost;
        this.flag = true;

        setStepsRemaining();
    }

    @Override
    public void log(Players player) {
        //logB.txt dosyasi olustur ve icine hamle bilgilerini yazdir.

        try {
            writer = new FileWriter("src/sample/Logs/logB.txt",true);
            writer.write("\nB Oyuncusu\n\n");
            writer.write("Kalan Altın Miktarı : "+player.gold+" \nHarcanan Toplam Altın Miktarı :"+player.goldSpent
                    +" \nToplam Atılan Adım Sayısı : "+player.stepsCount);
            writer.write("\n\n----\n");
            writer.close();

        }
        catch (Exception e) {
            System.out.println("logB.txt dosyasına yazma işlemi sırasında bir sorun meydana geldi.");
            e.printStackTrace();
        }

    }

    @Override
    public void movement(int[][] matrix,Players a,Players c,Players d) {

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
            if(this.indexI == a.targetI && this.indexJ == a.targetJ){

                System.out.println("B oyuncusu,A oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                a.flag = false;

            } if(this.indexI == c.targetI && this.indexJ == c.targetJ){

                System.out.println("B oyuncusu,C oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                c.flag = false;

            } if(this.indexI == d.targetI && this.indexJ == d.targetJ){

                System.out.println("B oyuncusu,D oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                d.flag = false;

            }

            if(this.indexI == targetI && this.indexJ == this.targetJ && matrix[this.indexI][this.indexJ] != 1){
                System.out.println("B oyuncusu hedefine ulaştı.");
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
