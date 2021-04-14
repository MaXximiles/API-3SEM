import "./Modal.css";
import React, { useEffect, useLayoutEffect, useRef, useState } from "react";
import ReactDOM from "react-dom";

const Modal = ({
  title,
  isOpen,
  setIsOpen,
  size = "standard",
  forceChoice = false,
  children,
}) => {
  const [wasModalOpen, setWasModalOpen] = useState(isOpen);
  const [modalAnimation, setModalAnimation] = useState("");
  const [modalContentAnimation, setModalContentAnimation] = useState("");
  const containerRef = useRef();
  const ref = useRef();

  const hasElementOverflown = ({
    clientWidth,
    clientHeight,
    scrollWidth,
    scrollHeight,
  }) => {
    return scrollHeight > clientHeight || scrollWidth > clientWidth;
  };

  useEffect(() => {
    const onBodyClick = (event) => {
      if (ref.current && ref.current.contains(event.target)) {
        return;
      }

      setIsOpen(false);
    };

    const onResize = () => {
      document.body.classList.toggle(
        "scrolling",
        hasElementOverflown(containerRef.current)
      );

      ref.current.classList.toggle(
        "scrolling",
        hasElementOverflown(containerRef.current)
      );
    };

    if (!forceChoice) {
      document.body.addEventListener("mousedown", onBodyClick);
    }

    window.addEventListener("resize", onResize);

    return () => {
      document.body.removeEventListener("mousedown", onBodyClick);
      window.removeEventListener("resize", onResize);
    };
  }, [setIsOpen, forceChoice]);

  useLayoutEffect(() => {
    document.body.classList.toggle(
      "scrolling",
      hasElementOverflown(containerRef.current)
    );

    ref.current.classList.toggle(
      "scrolling",
      hasElementOverflown(containerRef.current)
    );

    if (wasModalOpen !== isOpen) {
      if (!wasModalOpen && isOpen) {
        setModalAnimation("animating fade in");
        setModalContentAnimation("animating scale in");
      } else {
        setModalAnimation("animating fade out");
        setModalContentAnimation("animating scale out");
      }

      setTimeout(() => {
        setWasModalOpen(isOpen);
      }, 250);
    } else {
      if (wasModalOpen && isOpen) {
        setModalAnimation("visible active");
        setModalContentAnimation("visible active");
      } else {
        setModalAnimation("hidden");
        setModalContentAnimation("hidden");
      }

      document.body.classList.toggle("dimmed", wasModalOpen && isOpen);
    }
  }, [isOpen, wasModalOpen]);

  return ReactDOM.createPortal(
    <div
      ref={containerRef}
      className={`ui dimmer modals page transition ${modalAnimation}`}
    >
      <div
        ref={ref}
        className={`ui ${size} modal transition ${modalContentAnimation}`}
      >
        {!forceChoice && (
          <i className="close icon" onClick={() => setIsOpen(false)}></i>
        )}
        <div className="header">{title}</div>
        {children}
      </div>
    </div>,
    document.body
  );
};

export default Modal;
