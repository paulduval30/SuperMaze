package supermaze.tools;

import supermaze.metier.Personnage;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Personnage>
{
    @Override
    public int compare(Personnage o1, Personnage o2)
    {
        return Integer.compare(o1.distanceFromEnd(), o2.distanceFromEnd());
    }
}
