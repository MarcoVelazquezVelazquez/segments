package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

import com.epam.rd.autotasks.segments.Point;

class Segment {

    Point start;
    Point end;

    public Segment(Point start, Point end) {

        if (start.getX() == end.getX() && start.getY() == end.getY()){
            throw new IllegalArgumentException();
        }

        this.start = start;
        this.end = end;
    }

    double length() {

        //d= sqr (x2 -x1)2 + (y2 - y1)2

        double distanceX = end.getX() - start.getX();
        double distanceY = end.getY() - start.getY();

        double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

        return distance;
    }

    Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);

    }

    Point intersection(Segment another) {

        double thisDirX = end.getX() - start.getX();
        double thisDirY = end.getY() - start.getY();
        double anotherDirX = another.end.getX() - another.start.getX();
        double anotherDirY = another.end.getY() - another.start.getY();

        // Calculate determinant and parameter values
        double det = thisDirX * anotherDirY - thisDirY * anotherDirX;
        double t1 = ((start.getX() - another.start.getX()) * anotherDirY - (start.getY() - another.start.getY()) * anotherDirX) / det;
        double t2 = ((start.getX() - another.start.getX()) * thisDirY - (start.getY() - another.start.getY()) * thisDirX) / det;

       // Check if the segments are collinear or don't intersect
        if (det == 0 || t1 < 0 || t1 > 1 || t2 < 0 || t2 > 1) {
            return null;
        }

        // Calculate the intersection point
        double interX = start.getX() + t1 * thisDirX;
        double interY = start.getY() + t1 * thisDirY;

        return new Point(interX, interY);

    }

}
