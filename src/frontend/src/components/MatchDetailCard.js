import { React } from "react";

export const MatchDetailCard = ({ teamName, match }) => {
  if (!match) return null;
  const otherTeam = match.team1 == teamName ? teamName : match.team2;
  return (
    <div className="MatchDetailCard">
      <h3>Latest Matches</h3>
      <h3>Match Details</h3>
      <h4>vs {otherTeam}</h4>
    </div>
  );
};
