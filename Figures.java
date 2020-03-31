abstract class Figure
{
	private String color, type;
	public Figure(String c, String t)
	{
		color=c;
		type=t;
	}
	public void setColor(String s)
	{
		color =s;
		Observer.modify();
	}
	abstract public double perimeter();
	public String toString()
	{
		return type+" - "+color+" - "+perimeter();
	}
	public boolean equals(Object o)
	{
		return((o instanceof Figure) && ((Figure)o).type.equals(type) && ((Figure)o).perimeter()==perimeter());
	}
}
class Circle extends Figure
{
	
	private double radius;
	public double perimeter()
	{
		return 2*Math.PI*radius;
	}
	public Circle(String c, String t, double r)
	{
		super(c, t);
		radius=r;
	}
	public void setRadius(double r)
	{
		radius=r;
		Observer.modify();
	}
}
class Square extends Figure
{
	private double side;
	public double perimeter()
	{
		return 4*side;
	}
	public Square(String c, String t, double s)
	{
		super(c, t);
		side=s;
	}
	public void setSide(double s)
	{
		side=s;
		Observer.modify();
	}
}
class Triangle extends Figure
{
	private double s1, s2, s3;
	public double perimeter()
	{
		return s1+s2+s3;
	}
	public Triangle(String c, String t, double s1, double s2, double s3)
	{
		super(c, t);
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
	}
	public void setSides(double s1, double s2, double s3)
	{
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
		Observer.modify();
	}
}
class Observer
{
	private Figure array[];
	private int max, n=0;
	//private static int count=0;
	private static Observer instance = null;
	private Observer(int x) 
	{
		array=new Figure[x];
		max = x;
	}
	public static synchronized Observer getInstance(int x) {
        if(instance == null) {
            instance = new Observer(x);
        }
        return instance;
    }
	/*public Observer(int x)
	{
		if(count==0)
		{
			array=new Figure[x];
			max=x;
			count++;
		}
	}*/
	public void addFigure(Figure f)
	{
		if(n<max)
		{
			array[n]=f;
			n++;
		}
		else
			System.out.println("out of storage");
	}
	public String toString()
	{
		String q="";
		for(int i=0;i<n;i++)
		{
			q=q+array[i].toString()+"\n";
		}
		return q;
	}
	public static void modify()
	{
		System.out.println("you have modified something");
		System.out.println(this.toString());
	}
	
}
class Client
{
	public static void main(String[] args)
	{
		Observer o = Observer.getInstance(10);
		Figure c1=new Circle("red", "circle", 3);
		Figure c2=new Circle("yellow", "circle", 9);
		Figure s1=new Square("green", "square", 4);
		Figure s2=new Square("cien", "square", 8);
		Figure t1=new Triangle("blue", "triangle", 3, 4, 5);
		Figure t2=new Triangle("maagenta", "triangle", 5, 11, 12);
		o.addFigure(c1);
		o.addFigure(s1);
		o.addFigure(t1);
		o.addFigure(c2);
		o.addFigure(s2);
		o.addFigure(t2);
		
		
		
		
	}
}
