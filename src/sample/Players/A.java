package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Path
//C:\Users\asus1\Desktop\Courses\Yazlab1\src\sample\Logs
public class A extends Players{

    public A(int moveCost,int targetCost,int steps,int gold,int indexI,int indexJ,int matrixM,int matrixN){

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

    public int getIndexI(){
        return this.indexI;
    }

    public int getIndexJ(){
        return this.indexJ;
    }

    //En yakindaki altini hedefler
    @Override
    public void selectTarget(int matrix[][],int targetCost,int playerI,int playerJ){
        //Search algorithm
        int tempDist = 1000;
        int tempI = 0,tempJ = 0;
        playerI = this.indexI;
        playerJ = this.indexJ;

        for (int i=0;i<matrixM;i++){
            for(int j=0;j<matrixN;j++){
                if(matrix[i][j]!=1 && calcDistance(playerI,playerJ,i,j) < tempDist){
                    tempDist = calcDistance(playerI,playerJ,i,j);
                    tempI = i;
                    tempJ = j;
                }
            }
        }


        System.out.println("A oyuncusunun hedefi "+tempI+","+tempJ+" ve hedefteki altın miktarı : "+matrix[tempI][tempJ]);

        this.gold -= targetCost;
        this.goldSpent += targetCost;
    }

    @Override
    public void log(Players player) {
        //logA.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        System.out.println(player.logFile.getName()+" dosyasına yazılıyor...");
        try {
            writer = new FileWriter("src/sample/Logs/logs.txt",true);
            writer.write("\nA Oyuncusu\n\n");
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



}
