    public class FallAndLandHere {
        public static void main(String args[]) {
            double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1;
            double x_target,x_power,x_fuel;
            double r;
            double g;
            
            g = -9.80665;
            
            x = 0;
            y = 100000;
            x_initial_speed = 800;
            x_wind_speed1 = 200;
            x_power = 5;
            x_fuel = 20;
            x_target = 101000;
            
            x_speed = x_initial_speed;
            y_speed = 0;
            
            r = 100000;
            
            while( y >= 0 ) {
                y_speed += g/r;
                if( y > 8000 ) {
                    if( x_speed < x_initial_speed + x_wind_speed1 ) {
                        x_speed += 1.4/r;
                    } else if ( x_speed > x_initial_speed + x_wind_speed1 ) {
                        x_speed -= 1.4/r;
                    }
                } else {
                    x_speed = x_initial_speed;
                }
                
                if( x_fuel > 0 ) {
                    /* estimate where it is going to fall */
                    double est_x, est_y, est_x_speed, est_y_speed;
                    est_x = x;
                    est_y = y;
                    est_x_speed = x_speed;
                    est_y_speed = y_speed;
                    while( est_y >= 0 ) {
                        est_y_speed += g;
                        est_x += est_x_speed;
                        est_y += est_y_speed;
                    }
                    
                    /* use thruster */
                    if( est_x > x_target + 10 ) {
                        x_speed -= x_power/r;
                        x_fuel -= 0.1/r;
                    } else if ( est_x < x_target - 10 ) {
                        x_speed += x_power/r;
                        x_fuel -= 0.1/r;
                    }
                }
                
                x += x_speed/r;
                y += y_speed/r;
            }
            System.out.print("estimated distance = "+x+"\n");
            System.out.print("rest fuel = "+ x_fuel + "\n");
            
            return;
        }
    }
