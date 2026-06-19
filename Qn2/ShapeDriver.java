package Qn2;

public class ShapeDriver 
{
    
   
    public static void printAreas(Shape[] shapes) 
    {
        System.out.println("=== PRINTING POLYMORPHIC SHAPE AREAS ===");
        for (Shape s : shapes) 
        {
            
            System.out.printf("Shape Type: %-10s | Calculated Area: %.2f\n", 
                s.getClass().getSimpleName(), s.getArea());
        }
    }

    public static Shape largest(Shape[] shapes) 
    {
        if (shapes == null || shapes.length == 0) return null;
        
        Shape largestShape = shapes[0];
        for (Shape s : shapes) 
        {
            if (s.getArea() > largestShape.getArea())
            {
                largestShape = s;
            }
        }
        return largestShape;
    }

    public static void main(String[] args) {

        System.out.println("    EXCEPTION HANDLING DEMO        ");
   
        try
        {
            System.out.println("Attempting to create an invalid triangle (sides: 1, 1, 10)...");

            new Triangle("Red", true, 1.0, 1.0, 10.0);
        } catch (InvalidShapeException e)
        {
            System.out.println(">> Caught Expected Exception: " + e.getMessage());
        }

       // POLYMORPHISM & DATA PROCESSING 
        
        try 
        {
            Shape[] shapesArray = new Shape[] 
            {
                new Circle("Blue", true, 5.0),
                new Rectangle("Green", false, 4.0, 6.0),
                new Triangle("Purple", true, 3.0, 4.0, 5.0)
            };


            printAreas(shapesArray);
            
            Shape maxShape = largest(shapesArray);
            System.out.println("\n>>> LARGEST SHAPE SEARCH RESULT <<<");
            System.out.println(maxShape);
            System.out.printf("Area: %.2f\n", maxShape.getArea());
            System.out.println("=================================================");

        } catch (InvalidShapeException e) 
        {
            System.out.println("Unexpected creation breakdown: " + e.getMessage());
        }
    }
}