# Mingming Zheng - HW10
# GitHub Link: https://tinyurl.com/6uc872kr
# Folder: student-MingmingZheng/hw10/asteroids_game/

from laserbeam import LaserBeam
from asteroid import Asteroid
from spaceship import Spaceship

LIFE_LIMIT = 0
FIRST_INDEX = 0
DECREASE_UNIT = 1
INCREASE_UNIT = 1

class GameController:
    """
    Maintains the state of the game
    and manages interactions of game elements.
    """

    def __init__(self, SPACE, fadeout):
        """Initialize the game controller"""
        self.SPACE = SPACE
        self.fadeout = fadeout

        self.spaceship_hit = False
        self.asteroid_destroyed = False
        self.asteroids = [Asteroid(self.SPACE)]
        self.laser_beams = []
        self.spaceship = Spaceship(self.SPACE)

    def update(self):
        """Updates game state on every frame"""
        self.do_intersections()

        for asteroid in self.asteroids:
            asteroid.display()

        # ======================================================
        # Problem 3, Part 2: Laser beam handler
        # TODO: Your code will replace (or augment) the next several
        # lines. Laser beam objects should remain in the scene
        # as many frames as their lifespan allows.
        # Begin problem 3 code changes

        for l in range(len(self.laser_beams)):
            if self.laser_beams[l].lifespan > LIFE_LIMIT:
                self.laser_beams[l].display()

        # Delete beams out of list if expires
        for beam in list(self.laser_beams):
            if beam.lifespan <= LIFE_LIMIT:
                self.laser_beams.remove(beam)

        # End problem 3, part 2 code changes
        # =======================================================

        self.spaceship.display()

        # Carries out necessary actions if game over
        if self.spaceship_hit:
            if self.fadeout <= 0:
                fill(1)
                textSize(30)
                text("YOU HIT AN ASTEROID",
                     self.SPACE['w']/2 - 165, self.SPACE['h']/2)
            else:
                self.fadeout -= 1

        if self.asteroid_destroyed:
            fill(1)
            textSize(30)
            text("YOU DESTROYED THE ASTEROIDS!!!",
                 self.SPACE['w']/2 - 250, self.SPACE['h']/2)

    def fire_laser(self, x, y, rot):
        """Add a laser beam to the game"""
        x_vel = sin(radians(rot))
        y_vel = -cos(radians(rot))
        self.laser_beams.append(
            LaserBeam(self.SPACE, x, y, x_vel, y_vel)
            )

    def handle_keypress(self, key, keycode=None):
        if (key == ' '):
            if self.spaceship.intact:
                self.spaceship.control(' ', self)
        if (keycode):
            if self.spaceship.intact:
                self.spaceship.control(keycode)

    def handle_keyup(self):
        if self.spaceship.intact:
            self.spaceship.control('keyup')

    def do_intersections(self):
        # ======================================================
        # TODO: Problem 4, Part 1: Intersections
        # Here's where you'll probably want to check for intersections
        # between asteroids and laser beams. Laser beams should be removed
        # from the scene if they hit an asteroid, and the asteroid should
        # blow up (the blow_up_asteroid method also must be written. It's
        # been started for you below).
        #
        # The intersection logic below between the spaceship
        # and asteroids should give a hint as to how this will work.
        # Begin code changes for Problem 4, Part 1: Intersections

        if self.spaceship.intact:
            # Check each asteroid for intersection
            len_asteroids = len(self.asteroids)
            len_laser_beams = len(self.laser_beams)
            i = 0
            j = 0
            # Use while loop to iterate on list
            # to delete current item
            # and make sure it's within the range
            while i < len_asteroids:
                while j < len_laser_beams:
                    if (
                        abs(self.laser_beams[j].x - self.asteroids[i].x)
                        < max(self.asteroids[i].radius, self.laser_beams[j].radius)
                        and
                        abs(self.laser_beams[j].y - self.asteroids[i].y)
                        < max(self.asteroids[i].radius, self.laser_beams[j].radius)):
                        # Intersected an asteroid
                        self.blow_up_asteroid(i, j)
                        # Since the previous asteroid is poped
                        # Make sure interate on the new asteroid
                        # that stored on ith location
                        i -= DECREASE_UNIT
                        # Upated length
                        len_asteroids = len(self.asteroids)
                        # Remove laser beams
                        self.laser_beams[j].lifespan = LIFE_LIMIT
                    j += INCREASE_UNIT
                i += INCREASE_UNIT

        # End of code changes for Problem 4, Part 1: Intersections
        # ======================================================

        # If the space ship still hasn't been blown up
        if self.spaceship.intact:
            # Check each asteroid for intersection
            for i in range(len(self.asteroids)):
                if (
                      abs(self.spaceship.x - self.asteroids[i].x)
                      < max(self.asteroids[i].radius, self.spaceship.radius)
                      and
                      abs(self.spaceship.y - self.asteroids[i].y)
                      < max(self.asteroids[i].radius, self.spaceship.radius)):
                    # We've intersected an asteroid
                    self.spaceship.blow_up(self.fadeout)
                    self.spaceship_hit = True

    def blow_up_asteroid(self, i, j):
        # ======================================================
        # TODO: Problem 4, Part 2: Asteroid blow-up

        # Here you'll write the code to blow up an asteroid.
        # The parameters represent the indexes for the list of
        # asteroids and the list of laser beams, indicating
        # which asteroid is hit by which laser beam.

        # You'll need to:
        # A) Remove the hit asteroid from the scene
        # B) Add appropriate smaller asteroids to the scene
        # C) Make sure that the smaller asteroids are positioned
        #    correctly and flying off in the correct directions

        # Specifically. If the large asteroid is hit, it should
        # break into two medium asteroids, which should fly off
        # perpendicularly to the direction of the laser beam.

        # If a medium asteroid is hit, it should break into three
        # small asteroids, two of which should fly off perpendicularly
        # to the direction of the laser beam, and the third
        # should fly off in the same direction that the laser
        # beam had been traveling.

        # If a small asteroid is hit, it disappears.

        # Begin code changes for Problem 4, Part 2: Asteroid blow-up
        # Remove the target asteroid
        target_asteroid = self.asteroids.pop(i)
        # Get the velocity of hitted beam
        beam_x_vel = self.laser_beams[j].x_vel
        beam_y_vel = self.laser_beams[j].y_vel
        # Check which type of asteroid is hitted
        if (target_asteroid.asize == 'Large'):
            # Create 2 new med asteroids
            self.asteroids.append(Asteroid(
                                            target_asteroid.SPACE, asize='Med', 
                                            x=target_asteroid.x,
                                            y=target_asteroid.y, 
                                            x_vel=beam_y_vel, 
                                            y_vel=-beam_x_vel))
            self.asteroids.append(Asteroid(
                                            target_asteroid.SPACE, asize='Med', 
                                            x=target_asteroid.x,
                                            y=target_asteroid.y, 
                                            x_vel=-beam_y_vel, 
                                            y_vel=beam_x_vel))
        elif (target_asteroid.asize == 'Med'):
            # Create 3 new small asteroids
            self.asteroids.append(Asteroid(
                                            target_asteroid.SPACE, asize='Small', 
                                            x=target_asteroid.x,
                                            y=target_asteroid.y, 
                                            x_vel=beam_y_vel, 
                                            y_vel=-beam_x_vel))
            self.asteroids.append(Asteroid(target_asteroid.SPACE, asize='Small', 
                                            x=target_asteroid.x,
                                            y=target_asteroid.y, 
                                            x_vel=-beam_y_vel, 
                                            y_vel=beam_x_vel))
            self.asteroids.append(Asteroid(target_asteroid.SPACE, asize='Small', 
                                            x=target_asteroid.x,
                                            y=target_asteroid.y, 
                                            x_vel=beam_x_vel, 
                                            y_vel=beam_y_vel))
