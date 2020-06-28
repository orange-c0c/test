import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Snake {

	int score=0;//得分
	List<SnakeBody> bodyList = new ArrayList<SnakeBody>();//保存蛇的整个身体
	
	public Snake(){
		
	}
	
	public Snake(int x,int y){
		SnakeBody body=new SnakeBody(x,y);
		bodyList.add(body);
	}
	
	public void move(int x,int y){
		if(die(x,y)){//在移动之前预判下一步会不会挂
			return;
		}
		SnakeBody body=new SnakeBody(bodyList.get(0));
		bodyList.get(0).setX(bodyList.get(0).getX()+x);
		bodyList.get(0).setY(bodyList.get(0).getY()+y);
		bodyList.add(1,body);
		bodyList.remove(bodyList.size()-1);
		//判断是否吃到食物
		eat();
	}
	
	public boolean die(int x,int y){//判断蛇是否死亡
		SnakeBody head=new SnakeBody(bodyList.get(0));
		if(head.getX()+x<0||head.getX()+x>=50||head.getY()+y<0||head.getY()+y>=35){//是否出边界
			GreddySnake.start=false;
			return true;
		}
		for(int i=1;i<bodyList.size()-1;i++){//是否咬自己
			if(head.getX()+x==bodyList.get(i).getX()&&head.getY()+y==bodyList.get(i).getY()){
				GreddySnake.start=false;
				return true;
			}
		}
		return false;
	}
	
	public void eat(){
		if(GreddySnake.foodX==bodyList.get(0).getX()&&GreddySnake.foodY==bodyList.get(0).getY()){
			SnakeBody tempAct = new SnakeBody();
			tempAct.setX(bodyList.get(bodyList.size()-1).getX());
			tempAct.setY(bodyList.get(bodyList.size()-1).getY());
			bodyList.add(tempAct);
			Random ran=new Random();
			GreddySnake.foodX=ran.nextInt(50);
			GreddySnake.foodY=ran.nextInt(35);
		}
	}
}
