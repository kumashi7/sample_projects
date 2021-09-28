# Mingming Zheng - HW11 Connect Four
# Link: https://tinyurl.com/nmk4kfn8
# GitHub Folder: student-MingmingZheng/hw11/

import math
from disk import Disk

CYAN = (0, 102, 153)
GRAY = (0.75, 0.75, 0.75)
BLUE = (0.0, 0.0, 1.0)
TEXT_SIZE = 32
TEXT_X = 10 
TEXT_Y = 60
RADIUS = 50
VELOCITY = 5
TOTAL_COLUMN = 2
BOARD_STROKE = 20
BOARD_R_RANGE = 4
BOARD_C_RANGE = 3
DEFALUT_HEIGHT = 250
BLOCK_SIZE = 100
MAX_DISKS = 4


class GameController:
    """
    Maintains the state of the game
    and manages interactions of game elements.
    """
    def __init__(self, SPACE):
        """Initialize the game controller"""
        self.SPACE = SPACE
        self.disks = []
        self.falling_disk = None
        self.is_computer = False
        self.heights = []
        self.column_check = []
        for column in range(TOTAL_COLUMN):
            self.heights.append(DEFALUT_HEIGHT)
            self.column_check.append(True)
        self.game_over = False
        self.turn = "RED"

    def update(self):
        """Updates game state on every frame"""
        if self.falling_disk:
            self.falling_disk.display()
            # Check which column the falling disk at
            fall_column = self.get_column(self.falling_disk.x)
            # If disk's y is larger than height
            # and volecity is not 0
            if self.falling_disk.y >= self.heights[fall_column] and \
            self.falling_disk.y_vel > 0:
                # Stop the disk
                self.falling_disk.y_vel = 0
                if self.column_check[fall_column]:
                    # If the column still has rooms
                    self.disks.append(self.falling_disk)
                # Reduce column height
                self.heights[fall_column] -= BLOCK_SIZE
                # If the column is full
                if self.heights[fall_column] == RADIUS:
                    self.column_check[fall_column] = False
                # Remove falling disk after stored
                self.falling_disk = None
                # Switch to computer and change color
                self.is_computer = True
                if self.turn == "RED":
                    self.turn = "YELLOW"
                else:
                    self.turn = "RED"
        # Draw all existing disk
        if self.disks:
            for disk in self.disks:
                disk.display()
            if len(self.disks) == MAX_DISKS:
                self.draw_game_over()
                self.game_over = True
        # Draw the board
        self.draw_board()

    def handle_mousePressed(self, mouseX, mouseY):
        """Method that control key pressed interaction"""
        # Get the block coordinates
        (x, y) = self.board_coordinate(mouseX, mouseY)
        # Check if mouse is at top area
        if mouseY < BLOCK_SIZE:
            self.falling_disk = Disk(self.turn, x, y)

    def handle_mouseReleased(self):
        """Method that control key released interaction"""
        if self.falling_disk:
            # Get the falling column
            fall_column = self.get_column(self.falling_disk.x)
            # Change velocity if the column still has room
            if self.column_check[fall_column]:
                self.falling_disk.y_vel += VELOCITY
            # Otherwise remove falling disk
            else:
                self.falling_disk = None
        
    def draw_board(self):
        """Method that draws the board block"""
        strokeWeight(BOARD_STROKE)
        stroke(*BLUE)
        # Draw board lines
        for i in range(1, BOARD_R_RANGE):
            line(i, i * BLOCK_SIZE, DEFALUT_HEIGHT, i * BLOCK_SIZE)
        for j in range(BOARD_C_RANGE):
            line(j * BLOCK_SIZE, BLOCK_SIZE, j * BLOCK_SIZE, 
                 DEFALUT_HEIGHT + BLOCK_SIZE)

    def board_coordinate(self, mouseX, mouseY):
        """Method that returns falling block coordinates"""
        current_c = self.get_column(mouseX)
        x = current_c * BLOCK_SIZE + RADIUS
        y = RADIUS
        return (x, y)

    def get_column(self, x):
        """Method of return current column with given x"""
        return x // BLOCK_SIZE
    
    def draw_game_over(self):
        """Method that draws game over message"""
        fill(*CYAN)
        textSize(TEXT_SIZE)
        text("GAME OVER", TEXT_X, TEXT_Y)
