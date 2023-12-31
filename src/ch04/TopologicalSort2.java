package ch04;

import java.util.List;
import java.util.Stack;

/**
 * 순환이 없는 방향 그래프에 대해 위상 정렬
 * 
 * @author 김기태
 *
 */
public class TopologicalSort2 {
	private Stack<Node> stack; // 정점들을 방문한 역순으로 저장하는 스택

	/**
	 * 생성자<br>
	 * 빈 스택 생성
	 */
	public TopologicalSort2() {
		stack = new Stack<>();
	}

	/**
	 * 위상 정렬을 수행하는 메소드 
	 * @param v
	 */
	public void topologicalSort(Node v) {
		List<Node> neighbours = v.getNeighbours();	// 정점 v에 인접한 정점들의 연결 목록을 끄집어 낸다
		System.out.println("========방문한 노드 v: " + v.getInfo());
		System.out.print(v.getInfo() + "의 인접 정점들: ");
		neighbours.stream().forEach(neighbour -> {
			System.out.print(neighbour.getInfo() + " ");
		});
		System.out.println();

		// v에 인접한 모든 정점에 대해 깊이 우선 탐색을 한다
		System.out.println("깊이 우선 탐색");
		for (int i = 0; i < neighbours.size(); i++) {
			Node w = neighbours.get(i);
			System.out.println("정점 [" + w.getInfo() + "]에 대한 처리");
			if(w != null && !w.isVisited()) {
				w.setVisited(true);
				System.out.println("-->정점 [" + w.getInfo() + "] 방문 후 위상 정렬 호출");
				topologicalSort(w);
				System.out.println("<--위상 정렬 호출 후 정점 [" + w.getInfo() + "]" );
			}
		}
		stack.push(v);
		System.out.println("스택에 [" + v.getInfo() + "] 추가");

		System.out.println("현재 스택 상태 : ");
		stack.stream().forEach(node -> System.out.print(node.getInfo() + " | "));
		System.out.println();
	}
	
	public Stack<Node> getStack() {
		return stack;
	}

	public static void main(String[] args) {

		TopologicalSort2 topological = new TopologicalSort2();

		// 그래프에 깊이 우선 탐색을 시작하는 정점 0을 추가한다
		Node node0 = new Node(0);

		// 그래프에 있는 정점들을 생성한다
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		
		node0.addNeighbours(node1);		// 정점 0에서 진입 간선이 없는 정점 1로 가는 간선을 추가한다
		node0.addNeighbours(node3);
		node1.addNeighbours(node2);
		node1.addNeighbours(node4);
		node2.addNeighbours(node4);
		node2.addNeighbours(node6);
		node3.addNeighbours(node4);
		node3.addNeighbours(node5);
		node4.addNeighbours(node6);
		node5.addNeighbours(node6);
		node5.addNeighbours(node7);
		node6.addNeighbours(node7);
		
		System.out.println("위상 정렬 순서:");

		node0.setVisited(true);
		topological.topologicalSort(node0);
		
		// 스택을 가져 온다
		Stack<Node> resultStack = topological.getStack();
		
		// 스택에 있는 노드들을 출력한다
		while (resultStack.empty() == false)
			System.out.print(resultStack.pop().getInfo() + "  ");
	}	
}
