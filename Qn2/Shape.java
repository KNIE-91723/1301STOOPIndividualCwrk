package Qn2;

class InvalidShapeException extends Exception {
    public InvalidShapeException(String message) {
        super(message);
    }
}

public abstract class Shape {
    protected String color = "white";
    protected boolean filled;

    public Shape() { }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract void resize(double factor) throws InvalidShapeException;

    @Override
    public String toString() {
        return String.format("Color: %s | Filled: %b", color, filled);
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, boolean filled, double radius) throws InvalidShapeException {
        super(color, filled);
        if (radius <= 0) {
            throw new InvalidShapeException("Invalid Circle: Radius must be positive.");
        }
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize Failed: Factor must be positive.");
        }
        this.radius *= factor;
    }

    @Override
    public String toString() {
        return String.format("Circle [Radius: %.2f | %s]", radius, super.toString());
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, boolean filled, double width, double height) throws InvalidShapeException {
        super(color, filled);
        if (width <= 0 || height <= 0) {
            throw new InvalidShapeException("Invalid Rectangle: Dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize Failed: Factor must be positive.");
        }
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public String toString() {
        return String.format("Rectangle [Width: %.2f, Height: %.2f | %s]", width, height, super.toString());
    }
}

class Triangle extends Shape {
    private double s1, s2, s3;

    public Triangle(String color, boolean filled, double s1, double s2, double s3) throws InvalidShapeException {
        super(color, filled);
        if (s1 <= 0 || s2 <= 0 || s3 <= 0) {
            throw new InvalidShapeException("Invalid Triangle: Sides must be positive.");
        }
        if ((s1 + s2 <= s3) || (s1 + s3 <= s2) || (s2 + s3 <= s1)) {
            throw new InvalidShapeException("Invalid Triangle: Violation of Triangle Inequality Theorem.");
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
    }

    @Override
    public double getPerimeter() {
        return s1 + s2 + s3;
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize Failed: Factor must be positive.");
        }
        this.s1 *= factor;
        this.s2 *= factor;
        this.s3 *= factor;
    }

    @Override
    public String toString() {
        return String.format("Triangle [Sides: %.2f, %.2f, %.2f | %s]", s1, s2, s3, super.toString());
    }
}
