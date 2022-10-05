import { React } from "react";
import { Link } from "react-router-dom";
import "../components/TeamTile.scss";

export const TeamTile = ({ teamName }) => {
  return (
    <div className="teamtile">
      <Link to={`/teams/${teamName}`}>
        <h1 className="blocks">{teamName}</h1>
      </Link>
    </div>
  );
};
