import React, { useEffect, useRef, useState, useLayoutEffect } from "react";

const Dropdown = ({
  label,
  defaultText,
  options,
  selected,
  onSelectedChange,
}) => {
  const [isOpen, setOpen] = useState(false);
  const [search, setSearch] = useState("");
  const [hoveredOption, setHoveredOption] = useState(null);
  const [selectedSelections, setSelectedSelections] = useState([]);
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
    const filteredOptions = options.filter((option) => {
      if (selected.some(({ value }) => value === option.value)) {
        return false;
      }

      if (option.label.toLowerCase().includes(search.toLowerCase())) {
        return true;
      }

      return false;
    });

    const renderedOptions = filteredOptions.map((option, index) => {
      return (
        <div
          key={option.value}
          ref={hoveredOption === index ? selectedRef : null}
          className={`item ${hoveredOption === index ? "selected" : ""}`}
          onClick={() => onSelectedChange((selected) => [...selected, option])}
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

  const toggleSelection = (event, option) => {
    event.stopPropagation();

    if (event.shiftKey) {
      var initialIndex = selected.indexOf(
        selectedSelections[selectedSelections.length - 1]
      );
      var endIndex = selected.indexOf(option);

      if (initialIndex > endIndex) {
        const temp = initialIndex;
        initialIndex = endIndex;
        endIndex = temp;
      }

      setSelectedSelections((selectedSelections) => [
        ...selectedSelections,
        ...selected.filter(
          (selectedOption, index) => index >= initialIndex && index <= endIndex
        ),
      ]);
    } else if (event.ctrlKey) {
      if (selectedSelections.includes(option)) {
        console.log(selectedSelections);
        setSelectedSelections(
          selectedSelections.filter(
            (selected) => selected.value !== option.value
          )
        );
      } else {
        setSelectedSelections((selectedSelections) => [
          ...selectedSelections,
          option,
        ]);
      }
    } else {
      setSelectedSelections([option]);
    }
  };

  const unselectSelection = (event, option) => {
    event.stopPropagation();

    onSelectedChange(
      selected.filter(
        (selectedOption) =>
          !selectedSelections.includes(selectedOption) &&
          selectedOption !== option
      )
    );

    setSelectedSelections([]);
  };

  const onKeyDown = (event) => {
    if (!isOpen) {
      setOpen(true);
    }

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
          onSelectedChange((selected) => [
            ...selected,
            filteredOptions[hoveredOption],
          ]);
        }
        break;
      default:
        break;
    }
  };

  const renderedSelected = selected.map((option) => {
    return (
      <div
        key={option.value}
        className={`ui label transition visible ${
          selectedSelections.includes(option) ? "active" : null
        }`}
        style={{ display: "inline-block !important", cursor: "pointer" }}
        onClick={(e) => toggleSelection(e, option)}
      >
        {option.label}
        <i
          className="delete icon"
          onClick={(e) => unselectSelection(e, option)}
        ></i>
      </div>
    );
  });

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
          {isOpen && renderMenu()}
        </div>
      </div>
    </div>
  );
};

export default Dropdown;
