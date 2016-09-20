package database;

/**
 * Created by Ich on 12.09.2016.
 */
public class Highscore
{
    protected int id;
    protected String name;
    protected int time;
    protected int errors;
    protected int points;
    protected long when;
    protected int gameType;

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

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public int getErrors()
    {
        return errors;
    }

    public void setErrors(int errors)
    {
        this.errors = errors;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public long getWhen()
    {
        return when;
    }

    public void setWhen(long when)
    {
        this.when = when;
    }

    public int getGameType()
    {
        return gameType;
    }

    public void setGameType(int gameType)
    {
        this.gameType = gameType;
    }


}
