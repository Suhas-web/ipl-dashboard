import { React } from "react";

export const MatchSmallCard = ({ match, teamName }) => {
  if (!match) return null;
  const otherTeam = match.team1 == teamName ? teamName : match.team2;
  return (
    <div className="MatchSmallCard">
      <p>vs {otherTeam}</p>
    </div>
  );
};
