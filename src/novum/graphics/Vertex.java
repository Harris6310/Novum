package novum.graphics;

public class Vertex
{
	static int POSITION_ELEMENT_COUNT = 4;
	static int COLOR_ELEMENT_COUNT = 4;
	static int TEXTURE_ELEMENT_COUNT = 2;
	static int ELEMENT_COUNT = POSITION_ELEMENT_COUNT + COLOR_ELEMENT_COUNT + TEXTURE_ELEMENT_COUNT;
	
	static int ELEMENT_SIZE = 4;
	static int POSITION_SIZE = POSITION_ELEMENT_COUNT * ELEMENT_SIZE;
	static int COLOR_SIZE = COLOR_ELEMENT_COUNT * ELEMENT_SIZE;
	static int TEXTURE_SIZE = TEXTURE_ELEMENT_COUNT * ELEMENT_SIZE;
	static int SIZE = POSITION_SIZE + COLOR_SIZE + TEXTURE_SIZE;
	
	static int POSITION_LOCATION = 0;
	static int COLOR_LOCATION = POSITION_LOCATION + POSITION_SIZE;
	static int TEXTURE_LOCATION = COLOR_LOCATION + COLOR_SIZE;
	
	float x;
	float y;
	float z;
	float w;
	float r;
	float g;
	float b;
	float a;
	float s;
	float t;
	
	public Vertex(float x, float y, float z, float w, float r, float g, float b, float a, float s, float t)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.s = s;
		this.t = t;
	}
	
	public float[] getData()
	{
		float[] data = new float[ELEMENT_COUNT];
		
		data[0] = x;
		data[1] = y;
		data[2] = z;
		data[3] = w;
		data[4] = r;
		data[5] = g;
		data[6] = b;
		data[7] = a;
		data[8] = s;
		data[9] = t;
		
		return data;
	}
}