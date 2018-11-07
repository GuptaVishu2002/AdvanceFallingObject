import java.io.*;
  
    public class FallInWind {
        public static void main(String args[]) { 
            double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1;
            double r;
            double g;
            
            g = -9.80665;
            
            x = 0;
            y = 100000;
            x_initial_speed = 800;
            
            x_speed = x_initial_speed;
            y_speed = 0;
            
            r = 100000;
            String buf;
            
            try{
            	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Enter Wind Speed: ");
            	buf = br.readLine();
            	x_wind_speed1 = Double.parseDouble(buf);
            
            while(y>=0) {
                y_speed += g/r;
                if( y > 8000 ) {
                    x_speed = x_initial_speed + x_wind_speed1;
                } else {
                    x_speed = x_initial_speed;
                }
                
                x += x_speed/r;
                y += y_speed/r;
            }
			
			int aX = (int)x;
			int bX = aX%100;
			if(bX<=50){
			aX = aX - bX;
			}
			else{
			bX=100-bX;
			aX=aX+bX;
			}
				
            System.out.print("estimated distance = "+x+"\n");
            System.out.print("estimated distance is approximately : "+aX+"\n");
            }catch(Exception e)
            {
            	System.out.print("Error:"+e);
            }
            
            return;
        }
    }
