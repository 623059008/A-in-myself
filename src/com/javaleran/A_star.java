package com.javaleran;

public class A_star {
   private int[] start={0,0};//单起点
   private int[] end={0,0};//单终点
   private int[] now={0,0};//目前位置
   private char[][] obstacle=new char[100][2];
   private char[][] map=new char[100][100];
  // private char[][] success=new char[100][100];
/*
 * 开放接口	
 */
public void begin(A_star object,char[][] map)
{
	object.map=map;
	object.get_map_inf(object,object.map);
	if (object.map[object.start[0]][object.start[1]]!='S')
	{
		System.err.println("地图不规范，没有起点S");return;
	}
	if (object.map[object.end[0]][object.end[1]]!='E')
	{
		System.err.println("地图不规范，没有终点E");return;
	}
	object.now[0]=object.start[0];
	object.now[1]=object.start[1];
	object.nextone(object, object.map);
	}

public void print_map(char[][] map)
{
	String s = new String();
	s="";
	for (int i=0;i<map.length;i++)
	{
		for (int j=0;j<map[i].length;j++)
		{
			if (j==0)
			{
				s+="|";
			}
			s+=map[i][j]+"|";
			if (j==map[i].length-1)
			{
				s+="\n";
			
			}
		}
	
	}
	s=s.substring(0,s.length()-1);
/*	替换H字符
 * while (s.indexOf("H")!=-1)
	{
	int search=s.indexOf("H");
	System.out.print(s.substring(0,search));
	System.out.print("L");
	s=s.substring(search+1,s.length());
	}*/
	System.out.println(s);
	System.out.println("----------------------------");
}
/*
 * 私有方法
 */


/*
 * 寻求下一步的方法(试探未知)
 */
private void nextone(A_star object,char[][] map)
{
	mark_way(object,object.now[0],object.now[1]);
	long 
	      x1=9223372036854775807L,
		  x2=9223372036854775807L,
		  x3=9223372036854775807L,
		  x4=9223372036854775807L;
	int a1,a2,a3,a4;
	try{a1=testisok(map[object.now[0]-1][object.now[1]]);}
	catch(Exception e)
	{
		a1=-1;
		System.err.println("There is an error1");
	}
	try{a2=testisok(map[object.now[0]+1][object.now[1]]);}
	catch(Exception e)
	{
		a2=-1;
		System.err.println("There is an error2");
	}
	try{a3=testisok(map[object.now[0]][object.now[1]-1]);}
	catch(Exception e)
	{
		a3=-1;
		System.err.println("There is an error3");
	}
	try{a4=testisok(map[object.now[0]][object.now[1]+1]);}
	catch(Exception e)
	{
		a4=-1;
		System.err.println("There is an error4");
	}
	if (a1==2 || a2==2 || a3==2 || a4==2)
	{object.print_map(object.map);return;}
	if (a1==-1 || a2==-1 || a3==-1 || a4==-1)
	{System.err.println("There is an error!");return;}
	if (a1==1)
	{
		x1=predicted(object,object.now[0]-1,object.now[1]);
	}
	if (a2==1)
	{
		x2=predicted(object,object.now[0]+1,object.now[1]);
	}
	if (a3==1)
	{
		x3=predicted(object,object.now[0],object.now[1]-1);
	}
	if (a4==1)
	{
		x4=predicted(object,object.now[0],object.now[1]+1);
	}
	long re=get_min(x1,x2,x3,x4);
	//System.out.println("x1:"+x1+"\nx2:"+x2+"\nx3:"+x3+"\nx4:"+x4+"\nre:"+re);
	//显示算法的选路过程
	if (re<9223372036854775807L)
	{
	if (re==x1){object.now[0]=object.now[0]-1;object.now[1]=object.now[1];}
	else{
	if (re==x2){object.now[0]=object.now[0]+1;object.now[1]=object.now[1];}
	else{
	if (re==x4){object.now[0]=object.now[0];object.now[1]=object.now[1]+1;}
	else{
	if (re==x3){object.now[0]=object.now[0];object.now[1]=object.now[1]-1;}
	}
	}
	}
	}else{
		if (object.now[0]==object.start[0] && object.now[1]==object.start[1])
		{
			object.print_map(object.map);
			System.out.println("该地图没有路径到达终点");
			return;
		}else{
		object.now[0]=object.start[0];
		object.now[1]=object.start[1];}
	}
	nextone(object,map);//递归
}
/*
 * 标记走过的路(留下足迹)
 */
private void mark_way(A_star object,int x,int y)
{
	if (object.map[x][y]=='#')
	{
	object.map[x][y]='H';
	//print_map(object.map);
	//显示运行路径
	}
	}
/*
 *简单的预测方法(终点迫近)
 */
private long predicted(A_star object,int a,int b)
{
	long x=(object.end[0]-a)*(object.end[0]-a)+(object.end[1]-b)*(object.end[1]-b);
	return x;
	}
/*
 * 在四个数字中取得最小值的方法(最优之数)
 */
private long get_min(long a,long b,long c,long d)
{
	if (b<=a)
	{
		a=b;
	}
	if (c<=a)
	{
		a=c;
	}
	if (d<=a)
	{
		a=d;
	}
	return a;
	}
/*
 * 判断目标是否可走的方法(读取信息)
 */
private int testisok(char ch)
{
	switch (ch)
	{
	case'#':return 1;
	case'W':return 0;
	case'E':return 2;
	case'S':return 3;
	case'H':return 0;
	default:System.out.println(ch);return -1;
	}
	
	}
/*
 * 获取地图基本信息的方法
 */
private void get_map_inf(A_star object,char[][] map){
		for(int i=0;i<object.obstacle.length;i++)
		{
			for(int j=0;j<object.obstacle[i].length;j++)
			{
				object.obstacle[i][j]='#';
			}
		}
		int w=0;
		
		for (int i=0;i<map.length;i++)
		{
			for (int j=0;j<map[i].length;j++)
			{
				if(map[i][j]=='S')
				{
					object.start[0]=i;
					object.start[1]=j;
				}
				if(map[i][j]=='E')
				{
					object.end[0]=i;
					object.end[1]=j;
				}
				if (map[i][j]=='W')
				{
					object.obstacle[w][0]=(char)(i+48);//这样可以把i转成对应的数字字符
					object.obstacle[w][1]=(char)(j+48);
					w++;
				}
				
			}
			
		}
	}

public static void main(String[] args) {
		char[][] Xmap={
				{'W','W','W','W','W','W','W','W','W','W'},
				{'W','#','#','#','#','#','#','W','E','W'},
				{'W','#','W','W','W','W','#','W','#','W'},
				{'W','#','W','W','W','W','#','W','#','W'},
				{'W','#','#','#','S','W','#','W','#','W'},
				{'W','W','W','W','W','W','#','W','#','W'},
				{'W','#','#','#','W','W','#','W','#','W'},
				{'W','#','#','#','#','#','#','#','#','W'},
				{'W','W','W','W','W','W','W','W','W','W'},
		};
	A_star one= new A_star();//初始化对象
	one.begin(one,Xmap);//启动算法！！！！
	//one.print_map(map);//打印地图
		

	}


}
