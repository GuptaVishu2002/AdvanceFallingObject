public class FallAndLandAdv{

	public static final double ROfEarth =  6371000;	
		public static final double g = -9.80665;				

		public static double CalcG(double length)
	{
		return Math.pow(ROfEarth/(ROfEarth+length),2) * g;
	}		
	
        public static void main(String args[]) {
            double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1, x_windspeed_est,y_max;
            double x_target,x_power,x_fuel;
            double r;
            double g;
            double gE, ROfEarth;
            double pi;
            double m,A,C,rho,D;
            int c;

            x = 0;
            y = 100000;
            x_initial_speed = 800;
            x_windspeed_est = 20;
            x_power = 10;
            x_fuel = 20;
            x_target = 141000;
            y_max = y;
            pi = 3.14159;
            
            m=.145 ;
  			A=pi*Math.pow(.0366*2,2)/4 ;
  			C=.5 ;
  			rho= 1.2 ;
  			D=rho*C*A/2 ;

            
            x_speed = x_initial_speed;
            y_speed = 0;
            

            
            r = 100000;
            
             System.out.print("<html><head><style type=\"text/css\">#flyingObject { position:absolute; left:0px; top:30px; width:3em; }#catchObject { position:absolute; left:0px; top:30px; width:3em; }</style><script type=\"text/javascript\">var flyobj = null;var catobj = null;var count = 0;var distmax = "+((int)(x_target)+100)+";var hmax = 100000;var theoval = "+x_target+";var movedata = new Array(");
        
       	    c = 0;
            
            while( y >= 0 ) {
            	gE = CalcG(y);
            	x_wind_speed1 = x_windspeed_est*Math.pow(y/y_max,0.6);
                y_speed += gE/r;
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
                    double est_x;
                    est_x = x + x_speed * (-y_speed - Math.sqrt( Math.pow(y_speed,2) - 2*gE*y))/gE;
                    /* use thruster */
                    if( est_x > x_target + 10 ) {
                        x_speed -= (x_power - D)/r;
                        x_fuel -= 0.1/r;
                    } else if ( est_x < x_target - 10 ) {
                        x_speed += (x_power - D)/r;
                        x_fuel -= 0.1/r;
                    }
                }
                
                 c++;
		        if( c >= r ) {
		            System.out.print(""+x+","+(100000-y)+",\n");
		            c = 0;
		            }
                
                x += x_speed/r;
                y += y_speed/r;
            }
            
            System.out.print("" + x + "," + (100000-y) );
            
            /* System.out.print("estimated distance = "+x+"\n");
            System.out.print("rest fuel = "+ x_fuel + "\n"); */
            
            System.out.print("); function doAnimLoop() {  flyobj.style.left = parseFloat(movedata[count])*800/distmax + 'px';  flyobj.style.top = parseFloat(movedata[count+1])*400/hmax + 'px';  count+=2;  if( count < movedata.length ) {    setTimeout(doAnimLoop,20);  } else {    if( (parseFloat(movedata[count-2]) - theoval > 3) ||      (parseFloat(movedata[count-2]) - theoval < -3)    ) {      flyobj.style.background = 'red';    } else {      flyobj.style.background = 'blue';    }  }}function doAnimStart() {  flyobj = document.getElementById('flyingObject');  flyobj.style.left='0px';  flyobj.style.top='0px';              catobj = document.getElementById('catchObject');  catobj.style.left=theoval*800/distmax +'px';  catobj.style.top=400+'px';  doAnimLoop();}window.onload = doAnimStart;</script></head><body><div id=\"flyingObject\">object</div><div id=\"catchObject\">catch</div></body></html>");
            
            return;
        }
    }
