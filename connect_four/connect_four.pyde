# Mingming Zheng - HW11 Connect Four
# Link: https://tinyurl.com/nmk4kfn8
# GitHub Folder: student-MingmingZheng/hw11/

from game_controller import GameController

GRAY = (0.75, 0.75, 0.75)
SPACE = {'w': 200, 'h': 300}

game_controller = GameController(SPACE)


def setup():
    """Set up the canvas"""
    size(SPACE['w'], SPACE['h'])
    colorMode(RGB, 1)


def draw():
    """Draw frames"""
    background(*GRAY)
    game_controller.update()
    

def mousePressed():
    """Handles functions when mouse pressed""" 
    if not game_controller.game_over:
        # Creatr new disk only when there's no disk
        # or there's a disk that stopped falling
        if (not game_controller.falling_disk) or \
            (game_controller.falling_disk and \
            game_controller.falling_disk.y_vel == 0):
            game_controller.handle_mousePressed(mouseX, mouseY)    


def mouseDragged():
    """Handles functions when mouse dragged"""
    if mousePressed:
        mousePressed()


def mouseReleased():
    """Handles functions when mouse released"""
    if not game_controller.game_over:
        if game_controller.falling_disk and \
        game_controller.falling_disk.y_vel == 0:
            game_controller.handle_mouseReleased()
