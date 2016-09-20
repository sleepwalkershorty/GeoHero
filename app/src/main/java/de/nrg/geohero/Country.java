package de.nrg.geohero;

public class Country
{
	protected int id;
	protected String name;
	protected String name_eng;
	protected String abbr;
	protected String capitol;
	protected String capitol_en;
	protected int color;
	protected int continent;
	protected int population;
	protected int pop_density;
	protected int area;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName_eng()
	{
		return name_eng;
	}

	public void setName_eng(String name_eng)
	{
		this.name_eng = name_eng;
	}

	public String getAbbr()
	{
		return abbr;
	}

	public void setAbbr(String abbr)
	{
		this.abbr = abbr;
	}

	public String getCapitol()
	{
		return capitol;
	}

	public void setCapitol(String capitol)
	{
		this.capitol = capitol;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public int getContinent()
	{
		return continent;
	}

	public void setContinent(int continent)
	{
		this.continent = continent;
	}

	public int getPopulation()
	{
		return population;
	}

	public void setPopulation(int population)
	{
		this.population = population;
	}

	public int getPop_density()
	{
		return pop_density;
	}

	public void setPop_density(int pop_density)
	{
		this.pop_density = pop_density;
	}

	public int getArea()
	{
		return area;
	}

	public void setArea(int area)
	{
		this.area = area;
	}

	public String getCapitol_en()
	{
		return capitol_en;
	}

	public void setCapitol_en(String capitol_en)
	{
		this.capitol_en = capitol_en;
	}
}
