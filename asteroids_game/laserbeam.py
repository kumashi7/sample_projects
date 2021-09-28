# Mingming Zheng - HW10
# GitHub Link: https://tinyurl.com/6uc872kr
# Folder: student-MingmingZheng/hw10/asteroids_game/

from flying_object import FlyingObject

# Problem 3, Part 1: Laser beam lifespans
#
# TODO: Implement laser beam lifespan and whatever
# further logic is needed to ensure that the
# laser beam's remaining lifespan is updated
# with each frame. You may make changes anywhere
# in this class definition.

# Set the lifespan to a default of 100 frames.

LIFESPAN = 100
LIFE_REDUCE = 1

class LaserBeam(FlyingObject):
    """A single laser torpedo"""
    def __init__(self, SPACE, x, y, x_vel, y_vel):
        self.LASER_SPEED_FACTOR = 5
        self.SPACE = SPACE
        self.rotation = 0.0
        self.radius = 2.5
        self.diam = self.radius*2
        self.x_vel = x_vel * self.LASER_SPEED_FACTOR
        self.y_vel = y_vel * self.LASER_SPEED_FACTOR
        self.x = x + x_vel
        self.y = y + y_vel
        self.lifespan = LIFESPAN

    def draw_me(self):
        # Reduce lifespan for each frame
        self.lifespan -= LIFE_REDUCE
        FILL_COLOR = 1
        fill(FILL_COLOR)
        noStroke()
        ellipse(0, 0, self.diam, self.diam)
