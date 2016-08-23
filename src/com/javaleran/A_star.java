package com.javaleran;

public class A_star {
   private int[] start={0,0};//�����
   private int[] end={0,0};//���յ�
   private int[] now={0,0};//Ŀǰλ��
   private char[][] obstacle=new char[100][2];
   private char[][] map=new char[100][100];
  // private char[][] success=new char[100][100];
/*
 * ���Žӿ�	
 */
public void begin(A_star object,char[][] map)
{
	object.map=map;
	object.get_map_inf(object,object.map);
	if (object.map[object.start[0]][object.start[1]]!='S')
	{
		System.err.println("��ͼ���淶��û�����S");return;
	}
	if (object.map[object.end[0]][object.end[1]]!='E')
	{
		System.err.println("��ͼ���淶��û���յ�E");return;
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
/*	�滻H�ַ�
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
 * ˽�з���
 */


/*
 * Ѱ����һ���ķ���(��̽δ֪)
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
	//��ʾ�㷨��ѡ·����
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
			System.out.println("�õ�ͼû��·�������յ�");
			return;
		}else{
		object.now[0]=object.start[0];
		object.now[1]=object.start[1];}
	}
	nextone(object,map);//�ݹ�
}
/*
 * ����߹���·(�����㼣)
 */
private void mark_way(A_star object,int x,int y)
{
	if (object.map[x][y]=='#')
	{
	object.map[x][y]='H';
	//print_map(object.map);
	//��ʾ����·��
	}
	}
/*
 *�򵥵�Ԥ�ⷽ��(�յ��Ƚ�)
 */
private long predicted(A_star object,int a,int b)
{
	long x=(object.end[0]-a)*(object.end[0]-a)+(object.end[1]-b)*(object.end[1]-b);
	return x;
	}
/*
 * ���ĸ�������ȡ����Сֵ�ķ���(����֮��)
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
 * �ж�Ŀ���Ƿ���ߵķ���(��ȡ��Ϣ)
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
 * ��ȡ��ͼ������Ϣ�ķ���
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
					object.obstacle[w][0]=(char)(i+48);//�������԰�iת�ɶ�Ӧ�������ַ�
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
	A_star one= new A_star();//��ʼ������
	one.begin(one,Xmap);//�����㷨��������
	//one.print_map(map);//��ӡ��ͼ
		

	}


}
