package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class C extends Players{

    int points[] = {5, 10, 15, 20};

    public C(int moveCost,int targetCost,int steps,int gold,int indexI,int indexJ,int matrixM,int matrixN){

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;
        this.indexI = indexI;
        this.indexJ = indexJ;
        this.matrixM = matrixM;
        this.matrixN = matrixN;

        try {
            this.logFile = new File("src/sample/Logs/logC.txt");
            if (logFile.createNewFile()) {
                System.out.println(logFile.getName() + " dosyası oluşturuldu.");
            }

        } catch (IOException e) {
            System.out.println("Bir hata meydana geldi.");
            e.printStackTrace();
        }

    }

    // Hedef belirlemeden once kendine en yakin belirli sayidaki gizli altini acar.
    // Bu açılan altınlarla birlikte tüm altınlar içerisinden B oyuncusu
    // gibi en karlı olanı hedefler
    @Override
    public void selectTarget(int matrix[][],int targetCost,int playerI,int playerJ){
        //Search algorithm
        Random random = new Random();
        double tempDist = 1000;
        double totalProfit = -100;
        double tempProfit ;
        int tempI = 0,tempJ = 0, secretI = 0,secretJ = 0;
        int secretGold = 1;
        boolean control = false;
        playerI = this.indexI;
        playerJ = this.indexJ;

        for (int i=0;i<matrixM;i++){
            for(int j=0;j<matrixN;j++){
                if( matrix[i][j] == 50 ){
                    control = true;
                }
            }
        }

        if(control){

            //Revealing the closest secret gold
            for (int i=0;i<matrixM;i++){
                for(int j=0;j<matrixN;j++){
                    if(matrix[i][j]==50 && calcDistance(playerI,playerJ,i,j) < tempDist){
                        tempDist = calcDistance(playerI,playerJ,i,j);
                        secretI = i;
                        secretJ = j;
                        secretGold = points[random.nextInt(4)];
                    }
                }
            }

            matrix[secretI][secretJ] = secretGold;

            System.out.println("\nC oyuncusu "+secretI+","+secretJ+" noktasındaki gizli altını açığa çıkardı.");
            System.out.println("Gizli altının değeri : "+secretGold);

            tempDist = 1000;

            //Revealing the closest secret gold
            for (int i=0;i<matrixM;i++){
                for(int j=0;j<matrixN;j++){
                    if(matrix[i][j]==50 && calcDistance(playerI,playerJ,i,j) < tempDist){
                        tempDist = calcDistance(playerI,playerJ,i,j);
                        secretI = i;
                        secretJ = j;
                        secretGold = points[random.nextInt(4)];
                    }
                }
            }

            matrix[secretI][secretJ] = secretGold;

            System.out.println("\nC oyuncusu "+secretI+","+secretJ+" noktasında ikinci bir gizli altını açığa çıkardı.");
            System.out.println("Gizli altının değeri : "+secretGold);

            tempDist = 1000;

        } else {
            System.out.println("Oyun tahtasında gizli altın kalmadı.\n");
        }



        for (int i=0;i<matrixM;i++){
            for(int j=0;j<matrixN;j++){
                if(matrix[i][j] != 1 && matrix[i][j]!=50){
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

        System.out.println("\nC oyuncusunun hedefi "+tempI+","+tempJ+" ve hedefteki altın miktarı : "+matrix[tempI][tempJ]);
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
        //logC.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        System.out.println(player.logFile.getName()+" dosyasına yazılıyor...");
        try {
            writer = new FileWriter("src/sample/Logs/logC.txt",true);
            writer.write("\nC Oyuncusu\n\n");
            writer.write("Kalan Altın Miktarı : "+player.gold+" \nHarcanan Toplam Altın Miktarı :"+player.goldSpent
                    +" \nToplam Atılan Adım Sayısı : "+player.stepsCount);
            writer.write("\n----\n");
            writer.close();
            System.out.println("Yazma işlemi tamamlandı!");
        }
        catch (Exception e) {
            System.out.println("Bir sorun meydana geldi.");
            e.printStackTrace();
        }

    }

    @Override
    public void movement(int[][] matrix,Players a,Players b,Players d) {

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

                System.out.println("C oyuncusu,A oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                a.flag = false;

            } else if(this.indexI == b.targetI && this.indexJ == b.targetJ){

                System.out.println("C oyuncusu,B oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                b.flag = false;

            } else if(this.indexI == d.targetI && this.indexJ == d.targetJ){

                System.out.println("C oyuncusu,D oyuncusunun hedeflediği altını aldı.");
                this.gold += matrix[this.indexI][this.indexJ];
                matrix[this.indexI][this.indexJ] = 1;
                d.flag = false;

            }

            if(this.indexI == targetI && this.indexJ == this.targetJ){
                System.out.println("C oyuncusu hedefine ulaştı.");
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
