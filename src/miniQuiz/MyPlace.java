package miniQuiz;

import java.util.ArrayList;
import java.util.Scanner;

class Coordinate {
    int x, y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 두 좌표가 같은지 확인하는 메서드
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    // 좌표 사이의 거리 계산 메서드
    public double distanceTo(Coordinate other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Myplace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 나의 좌표 입력받기
        System.out.print("나의 X 좌표를 입력하세요: ");
        int myX = scanner.nextInt();
        System.out.print("나의 Y 좌표를 입력하세요: ");
        int myY = scanner.nextInt();
        Coordinate myCoordinate = new Coordinate(myX, myY);

        // 임의의 좌표를 저장할 리스트
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        // 10개의 고유한 좌표 입력받기
        while (coordinates.size() < 10) {
            System.out.print("임의의 X 좌표를 입력하세요: ");
            int x = scanner.nextInt();
            System.out.print("임의의 Y 좌표를 입력하세요: ");
            int y = scanner.nextInt();
            Coordinate newCoordinate = new Coordinate(x, y);

            if (!coordinates.contains(newCoordinate)) {
                coordinates.add(newCoordinate);
            } else {
                System.out.println("중복된 좌표입니다. 다른 좌표를 입력하세요.");
            }
        }

        // 가장 가까운 좌표 찾기
        Coordinate closest = null;
        double minDistance = Double.MAX_VALUE;

        for (Coordinate coordinate : coordinates) {
            double distance = myCoordinate.distanceTo(coordinate);
            if (distance < minDistance) {
                minDistance = distance;
                closest = coordinate;
            }
        }

        // 결과 출력
        System.out.println("나의 좌표와 가장 가까운 좌표는 " + closest + " 입니다.");
        scanner.close();
    }
}
