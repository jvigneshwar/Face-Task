import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class RPS { 
    public static void main(String args[]) throws IOException{
        
        int hs=0,p1=0,p2=0,dc=0,player1_id = -1,player2_id = -1;
        String data[][] = new String[100][100];
        File f = new File("./hi.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s;
            
            while((s=br.readLine()) !=null){
                String arr[] = new String[2];
                arr = s.split(",");
                int score = parseInt(arr[1]);
                if(score > hs){
                    hs = score;
                }
                data[dc] = arr;
                dc++;
            }
//            for(int i=0;i<dc;i++){
//                System.out.println(Arrays.toString(data[i]));
//            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first player name : ");        
        String player1 = sc.next();
        for(int i=0;i<dc;i++){
            if(data[i][0].equals(player1)){
                System.out.println("existing user");
                player1_id = i;
                p1 = parseInt(data[i][1]);
            }
        }
        if(player1_id==-1){
            System.out.println("new user");
        }
        System.out.print("Enter the second player name : ");        
        String player2 = sc.next();
        if(player1.equals(player2)){
            System.out.println("player 1 and player 2 cannot be same");
        }
        for(int i=0;i<dc;i++){
            if(data[i][0].equals(player2)){
                System.out.println("existing user");
                player2_id = i;
                p2 = parseInt(data[i][1]);
            }
        }
        if(player2_id==-1){
            System.out.println("new user");
        }
        System.out.println();
        
        while(true){
                System.out.println("Highest Score : "+hs+" | "+player1+" : "+p1+" | "+player2+" : "+p2);
                    char pm1,pm2;   
                    while(true){
                        System.out.print(player1 + " give your move (R : Rock / P : Paper / S : Scissor) : ");
                        pm1 = sc.next().charAt(0);
                        if(pm1 !='R' && pm1!='P' && pm1!='S'){
                            System.out.println("Enter the correct option");
                        }
                        else{
                            break;
                        }
                    }
                    while(true){
                        System.out.print(player2 + " give your move (R : Rock / P : Paper / S : Scissor) : ");
                        pm2 = sc.next().charAt(0);
                        if(pm2 !='R' && pm2!='P' && pm2!='S'){
                            System.out.println("Enter the correct option");
                        }
                        else{
                            break;
                        }
                    }
//                    System.out.println(pm1 + " " + pm2);
                    if(pm1=='R'&&pm2=='S' || pm1=='P'&&pm2=='R' || pm1=='S'&&pm2=='P'){
                        p1++;
                        if(p1 > hs){
                            hs = p1;
                        }
                        System.out.println(player1 + " Won");
                    }
                    else if(pm2=='R'&&pm1=='S' || pm2=='P'&&pm1=='R' || pm2=='S'&&pm1=='P'){
                        p2++;
                        System.out.println(player2 + " Won");
                        if(p2 > hs){
                            hs = p2;
                        }
                    }
                    else{
                        System.out.println("Game Draw");
                    }
                    System.out.print("quit(y/n) ");
                    char opt = sc.next().charAt(0);
                    if(opt=='y'){
                        if(player1_id!=-1){
                            data[player1_id][1] = String.valueOf(p1);
                        }
                        else{
                            data[dc][0] = player1;
                            data[dc][1] = String.valueOf(p1);
                            dc++;
                        }
                        
                        if(player2_id!=-1){
                            data[player2_id][1] = String.valueOf(p2);
                        }
                        else{
                            data[dc][0] = player2;
                            data[dc][1] = String.valueOf(p2);
                            dc++;
                        }
                        for(int i=0;i<dc;i++){
                            System.out.println(data[i][0] + " " + data[i][1]);
                        }
                        try{
                            PrintWriter pw = new PrintWriter(new FileOutputStream(f,true));
                            BufferedWriter writer = Files.newBufferedWriter(Paths.get("./hi.txt"));
                            writer.write("");
                            writer.flush();
                            for(int i=0;i<dc;i++){
//                                System.out.println(data[i][0] + " " + data[i][1]);
                                pw.append(data[i][0]+","+data[i][1]+"\n");
                            }
                            pw.close();
                            System.out.println("updated");
                        }
                        catch(FileNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        return;
                    }
                    System.out.println();
            }
    }
}