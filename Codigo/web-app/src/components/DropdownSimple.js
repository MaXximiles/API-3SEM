import React, { useEffect, useRef, useState, useLayoutEffect } from "react";

const DropdownSimple = ({
  label,
  defaultText,
  options,
  selected,
  onSelectedChange,
}) => {
  const [isOpen, setOpen] = useState(false);
  const [search, setSearch] = useState("");
  const [hoveredOption, setHoveredOption] = useState(null);
  const [visible, setVisible] = useState(false);
  const [inputWidth, setInputWidth] = useState("auto");
  const inputRef = useRef();
  const measurerRef = useRef();
  const selectedRef = useRef();
  const ref = useRef();

  useEffect(() => {
    const onBodyClick = (event) => {
      if (ref.current && ref.current.contains(event.target)) {
        return;
      }

      setHoveredOption(null);
      setOpen(false);
    };

    document.body.addEventListener("click", onBodyClick);

    return () => {
      document.body.removeEventListener("click", onBodyClick);
    };
  }, []);

  useLayoutEffect(() => {
    if (selectedRef.current) {
      selectedRef.current.scrollIntoView(false);
    }
  }, [hoveredOption]);

  useEffect(() => {
    setVisible(true);
  }, [search]);

  useLayoutEffect(() => {
    if (visible) {
      const rect = measurerRef.current.getBoundingClientRect();
      setInputWidth(rect.width);
      setVisible(false);
    }
  }, [visible]);

  const openMenu = () => {
    if (inputRef.current) {
      inputRef.current.focus();
    }

    setOpen(!isOpen);
  };

  const renderMenu = () => {
    const filteredOptions = options.filter(
      (option) =>
        !selected === option &&
        option.label.toLowerCase().includes(search.toLowerCase())
    );

    const renderedOptions = filteredOptions.map((option, index) => {
      return (
        <div
          key={option.value}
          ref={hoveredOption === index ? selectedRef : null}
          className={`item ${hoveredOption === index ? "selected" : ""}`}
          onClick={() => onSelectedChange(option)}
        >
          {option.label}
        </div>
      );
    });

    if (renderedOptions.length) {
      return renderedOptions;
    }

    return <div className="message">Nenhum resultado encontrado.</div>;
  };

  const onKeyDown = (event) => {
    const filteredOptions = options.filter(
      (option) =>
        !selected.includes(option) &&
        option.label.toLowerCase().includes(search.toLowerCase())
    );

    if (filteredOptions.length === 0) {
      return;
    }

    if (
      (event.key === "ArrowDown" || event.key === "ArrowUp") &&
      hoveredOption === null
    ) {
      setHoveredOption(0);
      return;
    }

    switch (event.key) {
      case "ArrowDown":
        setHoveredOption(
          hoveredOption < filteredOptions.length - 1
            ? hoveredOption + 1
            : hoveredOption
        );
        break;
      case "ArrowUp":
        setHoveredOption(hoveredOption > 0 ? hoveredOption - 1 : hoveredOption);
        break;
      case "Enter":
        if (hoveredOption !== null) {
          onSelectedChange(filteredOptions[hoveredOption]);
        }
        break;
      default:
        break;
    }
  };

  const renderedSelected = selected.label;

  const renderedPlaceholder = <div className="default text">{defaultText}</div>;

  return (
    <div ref={ref} className="field">
      <label className="label">{label}</label>
      <div
        className={`ui fluid search multiple selection dropdown ${
          isOpen ? "visible active" : ""
        }`}
        onClick={openMenu}
      >
        <i className="dropdown icon" />
        {renderedSelected}
        <input
          ref={inputRef}
          className="search"
          value={search}
          onKeyDown={(e) => onKeyDown(e)}
          onChange={(e) => setSearch(e.target.value)}
          style={{ width: inputWidth + 1 }}
        ></input>
        <span ref={measurerRef} style={{ display: "inline" }} className="sizer">
          {visible && search}
        </span>
        {!search && renderedPlaceholder}
        <div className="text">{selected.label}</div>
        <div className={`menu ${isOpen ? "visible transition" : ""}`}>
          {renderMenu()}
        </div>
      </div>
    </div>
  );
};

export default DropdownSimple;
