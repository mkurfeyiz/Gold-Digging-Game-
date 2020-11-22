package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Path
//C:\Users\asus1\Desktop\Courses\Yazlab1\src\sample\Logs
public class A extends Players {

    public A(int moveCost, int targetCost, int steps, int gold, int indexI, int indexJ, int matrixM, int matrixN) {

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;
        this.indexI = indexI;
        this.indexJ = indexJ;
        this.matrixM = matrixM;
        this.matrixN = matrixN;

        try {
            this.logFile = new File("src/sample/Logs/logA.txt");
            if (logFile.createNewFile()) {
                System.out.println(logFile.getName() + " dosyası oluşturuldu.");
            }

        } catch (IOException e) {
            System.out.println("Bir hata meydana geldi.");
            e.printStackTrace();
        }

    }

    public int getIndexI() {
        return this.indexI;
    }

    public int getIndexJ() {
        return this.indexJ;
    }

    //En yakindaki altini hedefler
    @Override
    public void selectTarget(int matrix[][], int targetCost, int playerI, int playerJ) {
        //Search algorithm
        double tempDist = 1000;
        int tempI = 0,tempJ = 0;
        playerI = this.indexI;
        playerJ = this.indexJ;


        for (int i=0;i<matrixM;i++){
            for(int j=0;j<matrixN;j++){
                if(matrix[i][j]!=1 && matrix[i][j]!=50 && calcDistance(playerI,playerJ,i,j) < tempDist){
                    tempDist = calcDistance(playerI,playerJ,i,j);
                    tempI = i;
                    tempJ = j;
                }
            }
        }

        this.targetI = tempI;
        this.targetJ = tempJ;
        this.targetDist = (int) Math.ceil(tempDist/this.steps);

        System.out.println("\nA oyuncusunun hedefi "+tempI+","+tempJ+" ve hedefteki altın miktarı : "+matrix[tempI][tempJ]);
        System.out.println("Yapacağı Hamle Sayısı : "+targetDist);

        this.gold -= targetCost;
        this.goldSpent += targetCost;
        this.flag = true;

        setStepsRemaining();
        //System.out.println("hedefe olan adım sayısı "+this.stepsRemaining);
    }

    @Override
    public void log(Players player) {
        //logA.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        System.out.println(player.logFile.getName() + " dosyasına yazılıyor...");
        try {
            writer = new FileWriter("src/sample/Logs/logA.txt", true);
            writer.write("\nA Oyuncusu\n\n");
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

    @Override
    public void movement(int[][] matrix,Players b,Players c,Players d) {
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

                System.out.println("A oyuncusu,B oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                b.flag = false;

            } else if(this.indexI == c.targetI && this.indexJ == c.targetJ){

                System.out.println("A oyuncusu,C oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                c.flag = false;

            } else if(this.indexI == d.targetI && this.indexJ == d.targetJ){

                System.out.println("A oyuncusu,D oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                d.flag = false;

            }

            if(this.indexI == targetI && this.indexJ == this.targetJ){
                System.out.println("A oyuncusu hedefine ulaştı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                this.flag = false;
                break;
            }
        }

        this.gold -= this.moveCost;
        this.goldSpent += this.moveCost;
    }
}
