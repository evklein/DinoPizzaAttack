package com.hasherr.dinopizzaattack.math;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 10/31/13
 */
public class Vector2 // 2D vector class for positions and movements of entities.
{
    public double x,
                  y;

    public Vector2(double x, double y)
    {
        this.x = x; this.y = y;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void addVector(Vector2 vector)
    {
        this.x += vector.x;
        this.y += vector.y;
    }

    public void subVector(Vector2 vector)
    {
        this.x -= vector.x;
        this.y -= vector.y;
    }

    public void multByScalar(double scalarVal)
    {
        this.x *= scalarVal;
        this.y *= scalarVal;
    }

    public double getMagnitude()
    {
        return (Math.sqrt(getMagnitudeSquared()));
    }

    public double getMagnitudeSquared()
    {
        return (Math.pow(this.x, 2) + (Math.pow(this.y, 2)));
    }

    public double getDotProduct(Vector2 vector)
    {
        return ((this.x * vector.x) + (this.y * vector.y));
    }

    public Vector2 getNormalizedVector()
    {
        double magnitude = this.getMagnitude();

        double newX = this.x / magnitude;
        double newY = this.y / magnitude;

        return new Vector2(newX, newY);
    }

}
