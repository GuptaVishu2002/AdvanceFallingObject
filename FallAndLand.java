    public class FallAndLand {
        public static void main(String args[]) {
            double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1;
            double y_power,y_fuel,y_braking_point;
            double r;
            double g;
            
            g = -9.80665;
            
            x = 0;
            y = 100000;
            x_initial_speed = 800;
            x_wind_speed1 = 200;
            y_power = 50;
            y_fuel = 200;
            
            y_braking_point = 20000;
            
            x_speed = x_initial_speed;
            y_speed = 0;
            
            r = 100000;
            
            while( y >= 0 ) {
                y_speed += g/r;
                
                if( y_fuel > 0 ) {
                    /* use thruster */
                    if( y < y_braking_point && y_speed < -2.0 ) {
                        y_speed += y_power/r;
                        y_fuel -= 1.0/r;
                    }
                }
                
                x += x_speed/r;
                y += y_speed/r;
            }
            System.out.print("estimated distance = "+x+"\n");
            System.out.print("landing y speed = "+ y_speed + "\n");
            System.out.print("rest fuel = "+ y_fuel + "\n");
            
            return;
        }
    }
