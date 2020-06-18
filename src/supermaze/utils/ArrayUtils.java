package supermaze.utils;

public final class ArrayUtils
{
	private ArrayUtils() { }
	
	public static float[] intToFloat1D(int[] array)
	{
		float[] nArray = new float[array.length];
		for (int i = 0; i < nArray.length; i++)
			nArray[i] = array[i];
		return nArray;
	}
	
	public static int[] floatToInt1D(float[] array)
	{
		int[] nArray = new int[array.length];
		for (int i = 0; i < nArray.length; i++)
			nArray[i] = (int) array[i];
		return nArray;
	}
}
