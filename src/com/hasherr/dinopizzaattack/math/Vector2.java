package com.hasherr.dinopizzaattack.math;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class Vector2 // 2D vector class for positions and movements of entities.
{
    public double x;
    public double y;

    // Constructor; takes both x and y coordinate positions.
    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // Alternate constructor; sets both x and y coordinates to the same variable.
    public Vector2(double sameXY)
    {
        this.x = sameXY;
        this.y = sameXY;
    }

    // Sets the x position of this vector.
    public void setX(double x)
    {
        this.x = x;
    }

    // Sets the y position of this vector.
    public void setY(double y)
    {
        this.y = y;
    }

    // Adds another vector to this one.
    public void addVector(Vector2 vector)
    {
        this.x += vector.x;
        this.y += vector.y;
    }

    // Subtracts another vector from this class.
    public void subtractVector(Vector2 vector)
    {
        this.x -= vector.x;
        this.y -= vector.y;
    }

    // Multiplies this vector by a scalar value.
    public void multByScalar(double scalarVal)
    {
        this.x *= scalarVal;
        this.y *= scalarVal;
    }

    // Returns the magnitude of this vector.
    public double getMagnitude()
    {
        return (Math.sqrt(getMagnitudeSquared()));
    }

    // Returns the magnitude squared of this vector.
    public double getMagnitudeSquared()
    {
        return (Math.pow(this.x, 2) + (Math.pow(this.y, 2)));
    }

    // Returns the dot product of this vector and another vector; returns a vector.
    public double getDotProduct(Vector2 vector)
    {
        return ((this.x * vector.x) + (this.y * vector.y));
    }

    // Returns a normalized vector of the original (this) vector.
    public Vector2 getNormalizedVector()
    {
        double magnitude = this.getMagnitude();

        double newX = this.x / magnitude;
        double newY = this.y / magnitude;

        return new Vector2(newX, newY);
    }

}
