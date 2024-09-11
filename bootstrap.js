// Import our custom CSS
import '../scss/wordle.scss';

// Import Bootstrap Datepicker JS
import 'bootstrap-datepicker/dist/js/bootstrap-datepicker.js';

import { Alert, Button, Carousel, Collapse, Dropdown, Modal, Offcanvas, Popover, ScrollSpy, Tab, Toast, Tooltip } from 'bootstrap';
//import { Tooltip } from 'bootstrap';

function initTooltips() {
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new Tooltip(tooltipTriggerEl)
    });
}

initTooltips();