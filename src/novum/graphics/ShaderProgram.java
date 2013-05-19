package novum.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShaderProgram
{
	int objectID;
	int vertexShaderID;
	int fragmentShaderID;
	
	String vertexShaderLocation;
	String fragmentShaderLocation;
	
	public ShaderProgram(String vertexShaderLocation, String fragmentShaderLocation)
	{
		this.vertexShaderLocation = vertexShaderLocation;
		this.fragmentShaderLocation = fragmentShaderLocation;
	}
	
	public void initialise()
	{
		vertexShaderID = loadShader(vertexShaderLocation, GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentShaderLocation, GL_FRAGMENT_SHADER);
		objectID = glCreateProgram();

		glAttachShader(objectID, vertexShaderID);
		glAttachShader(objectID, fragmentShaderID);
		glLinkProgram(objectID);
		glBindAttribLocation(objectID, 0, "in_Position");
		glBindAttribLocation(objectID, 1, "in_Color");
		glBindAttribLocation(objectID, 2, "in_Texture");
		glValidateProgram(objectID);
		
		this.bind();
	}
	
	public void terminate()
	{
		this.free();
		
		glDetachShader(objectID, vertexShaderID);
		glDetachShader(objectID, fragmentShaderID);
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
		glDeleteProgram(objectID);
	}
	
	public void bind()
	{
		glUseProgram(objectID);
	}
	
	public void free()
	{
		
	}
	
	public int loadShader(String location, int type)
	{
		StringBuilder stringBuilder = new StringBuilder();
		int shaderID = 0;
		
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(location));
			String line;
			
			while((line = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
			
			bufferedReader.close();
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		
		shaderID = glCreateShader(type);
		
		glShaderSource(shaderID, stringBuilder);
		glCompileShader(shaderID);
		
		return shaderID;
	}
}