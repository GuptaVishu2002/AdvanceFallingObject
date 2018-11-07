    public class FallAndLandHereHTML {
        public static void main(String args[]) {
            double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1;
            double x_target,x_power,x_fuel;
            double r;
            double g;
            int c;
            
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
            
                    System.out.print("<html><head><style type=\"text/css\">#flyingObject { position:absolute; left:0px; top:30px; width:3em; }#catchObject { position:absolute; left:0px; top:30px; width:3em; }</style><script type=\"text/javascript\">var flyobj = null;var catobj = null;var count = 0;var distmax = "+((int)(x_target)+100)+";var hmax = 100000;var theoval = "+x_target+";var movedata = new Array(");
        
       	    c = 0;
            
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
                
                c++;
		        if( c >= r ) {
		            System.out.print(""+x+","+(100000-y)+",\n");
		            c = 0;
		        }
            }
            System.out.print("" + x + "," + (100000-y) );
            /* System.out.print("estimated distance = "+x+"\n");
            System.out.print("rest fuel = "+ x_fuel + "\n"); */
            
                    System.out.print("); function doAnimLoop() {  flyobj.style.left = parseFloat(movedata[count])*800/distmax + 'px';  flyobj.style.top = parseFloat(movedata[count+1])*400/hmax + 'px';  count+=2;  if( count < movedata.length ) {    setTimeout(doAnimLoop,20);  } else {    if( (parseFloat(movedata[count-2]) - theoval > 3) ||      (parseFloat(movedata[count-2]) - theoval < -3)    ) {      flyobj.style.background = 'red';    } else {      flyobj.style.background = 'blue';    }  }}function doAnimStart() {  flyobj = document.getElementById('flyingObject');  flyobj.style.left='0px';  flyobj.style.top='0px';              catobj = document.getElementById('catchObject');  catobj.style.left=theoval*800/distmax +'px';  catobj.style.top=400+'px';  doAnimLoop();}window.onload = doAnimStart;</script></head><body><div id=\"flyingObject\">object</div><div id=\"catchObject\">catch</div></body></html>");
        
            
            return;
        }
    }
