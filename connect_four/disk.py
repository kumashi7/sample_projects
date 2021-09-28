# Mingming Zheng - HW11 Connect Four
# Link: https://tinyurl.com/nmk4kfn8
# GitHub Folder: student-MingmingZheng/hw11/

RADIUS = 100
RED = (1.0, 0, 0)
YELLOW = (1.0, 1.0, 0.5)


class Disk:
        """
        A yellow or a red disk
        """
        def __init__(self, color, x, y):
            """Initialize a disk"""
            self.x = x
            self.y = y
            self.y_vel = 0
            self.size = RADIUS
            # Assign color to red or yellow
            if color == "RED":
                self.color = RED
            elif color == "YELLOW":
                self.color = YELLOW
        
        def draw_disk(self):
            """Draw a disk"""
            strokeWeight(0)
            fill(*self.color)
            circle(self.x, self.y, self.size)
            self.y += self.y_vel

        def display(self):
            """Display disks of certain places"""
            self.draw_disk()
