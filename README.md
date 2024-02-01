# lab1: DECIDE

### Java Implementation of a boolean DECIDE function for missile systems.

DECIDE is a function to determine if a missile system should launch a missile based on radar data.
It writes YES to standard out if the missile should be fired, and NO if it should not.

### How to compile
1. Set Java version to Java 21.
2. Run `./gradlew build` (or import into gradle-compatible IDE).

### How to use:
Library defines function DECIDE which takes an array of coordinates and the size of the array, 
as well as a parameter object containing various parameters, the logical combination 
matrix and the primary unlocking vector. See the code for more details.
```java
void DECIDE(
    int NUMPOINTS, Point2D.Double[] POINTS,
    Parameters PARAMETERS, LCMOperators[][] LCM, boolean[] PUV
);
```

### Statement of Contributions:
#### Douglas Fischer (DouglasFischer):
Implemented LIC1, LIC2, LIC5, LIC9, and LIC13.  
Created tests for LIC6, LIC10, and LIC14.  
Reviewed LIC3, LIC7 and LIC11.  
Created PUM and FUV calculators and the associated tests.  

#### Erik Winbladh (ractodev):
Implemented LIC0, LIC4, LIC8, LIC12.  
Created tests for LIC1, LIC5, LIC9, and LIC 13.  
Reviewed LIC2, LIC6, LIC10, LIC14.  
Created PUM and associated tests.  
Implemented DECIDE and associated tests.  

#### Johan Norlin (Acuadragon100):
Implemented LIC6, LIC10 and LIC14.  
Created tests for LIC2, LIC3, LIC7 and LIC11.  
Reviewed and merged LIC4, LIC8 and LIC12.  
Wrote documentation for various tests.  

#### Robin Claesson (RobinClaesson):
Implemented LIC3, LIC7, and LIC11.  
Created tests for LIC0, LIC4, LIC8, and LIC12.  
Reviewed and merged LIC1, LIC5, LIC9, and LIC13.  
Wrote documentation for various tests.  